package io.pivotal.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import io.pivotal.web.domain.CompanyInfo;
import io.pivotal.web.domain.MarketSummary;
import io.pivotal.web.domain.Order;
import io.pivotal.web.domain.Portfolio;
import io.pivotal.web.domain.Quote;
import io.pivotal.web.exception.OrderNotSavedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@EnableScheduling
@RefreshScope
public class MarketService {
	private static final Logger logger = LoggerFactory
			.getLogger(MarketService.class);
	private final static Integer QUOTES_NUMBER = 3;

	//Every 30 Secs
	private final static long REFRESH_PERIOD = 30000l;
	
	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

    @Value("${pivotal.app.symbols.it:EMC,IBM,VMW}")
	private String symbolsIT;
    @Value("${pivotal.app.symbols.fs:JPM,C,MS}")
	private String symbolsFS;
	
	private MarketSummary summary = new MarketSummary();
	
	public MarketSummary getMarketSummary() {
		logger.debug("Retrieving Market Summary");
		
		return summary;
	}
	
	@HystrixCommand(fallbackMethod = "getQuoteFallback")
	public Quote getQuote(String symbol) {
		logger.debug("Fetching quote: " + symbol);

		//manually trip the circuit
		if("xyz".equalsIgnoreCase(symbol)) throw new RuntimeException("That isn't a valid name!!!");

		Quote quote = restTemplate.getForObject("http://quote-service/quote/{symbol}", Quote.class, symbol);
		return quote;
	}
	
	private Quote getQuoteFallback(String symbol) {
		logger.debug("Fetching fallback quote for: " + symbol);
		//Quote quote = restTemplate.getForObject("http://quotes/quote/{symbol}", Quote.class, symbol);
		Quote quote = new Quote();
		quote.setSymbol(symbol);
		quote.setStatus("FAILED");
		return quote;
	}

	@HystrixCommand(fallbackMethod = "getCompaniesFallback")
	public List<CompanyInfo> getCompanies(String name) {
		logger.debug("Fetching companies with name or symbol matching: " + name);

        //manually trip the circuit
        if("xyz".equalsIgnoreCase(name)) throw new RuntimeException("That isn't a valid name!!!");

		CompanyInfo[] infos = restTemplate.getForObject("http://quote-service/company/{name}", CompanyInfo[].class, name);
		return Arrays.asList(infos);
	}
	private List<CompanyInfo> getCompaniesFallback(String name) {
		List<CompanyInfo> infos = new ArrayList<>();
		return infos;
	}
	
	
	
	public Order sendOrder(Order order ) throws OrderNotSavedException{
		logger.debug("send order: " + order);
		
		//check result of http request to ensure its ok.
		
		ResponseEntity<Order>  result = restTemplate.postForEntity("http://portfolio-service/portfolio/{accountId}", order, Order.class, order.getAccountId());
		if (result.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
			throw new OrderNotSavedException("Could not save the order");
		}
		logger.debug("Order saved:: " + result.getBody());
		return result.getBody();
	}
	
	public Portfolio getPortfolio(String accountId) {
		Portfolio folio = restTemplate.getForObject("http://portfolio-service/portfolio/{accountid}", Portfolio.class, accountId);
		logger.debug("Portfolio received: " + folio);
		return folio;
	}
	
	//TODO: prime location for a redis/gemfire caching service!
	@Scheduled(fixedRate = REFRESH_PERIOD)
	protected void retrieveMarketSummary() {
		logger.debug("Scheduled retrieval of Market Summary");

        logger.debug("IT symbols: " + symbolsIT);
        logger.debug("FS symbols: " + symbolsFS);

		List<Quote> quotesIT = pickRandomThree(Arrays.asList(symbolsIT.split(","))).parallelStream().map(symbol -> getQuote(symbol)).collect(Collectors.toList());
		List<Quote> quotesFS = pickRandomThree(Arrays.asList(symbolsFS.split(","))).parallelStream().map(symbol -> getQuote(symbol)).collect(Collectors.toList());
		summary.setTopGainers(quotesIT);
		summary.setTopLosers(quotesFS);
	}
	
	private List<String> pickRandomThree(List<String> symbols) {
		List<String> list = new ArrayList<>();
		Collections.shuffle(symbols);
	    list = symbols.subList(0, QUOTES_NUMBER);
	    return list;
	}
}
