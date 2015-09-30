
module.exports = function (app) {

    var reqCount = 0;

	app.get('/stats/:name?', function (req, res, next) {

		var name = req.params.name;

		var sorter = function(objectToSort){
			var sortable = [];
			for (var key in objectToSort) {
				var value = objectToSort[key];
//				if (typeof value === 'string' || value instanceof String) {
					sortable.push([key, value]);
//				} else {
//					sortable.push([key, sorter(value) + 'OBJECT XX']);
//				}
			}
			sortable.sort(function(a, b) {
				var nameA=a[0], nameB=b[0];
				if (nameA < nameB) {
					return -1;
				}
				if (nameA > nameB) {
					return 1;
				}
				return 0;
			});
			return sortable;
		};

		res.render('stats', {
			name: name,
			everything: process.env,
			properties: sorter(process.env)
		});

	});

	app.get('/killSwitch', function (req, res) {
		process.exit(0);
	});

	app.get('/*', function (req, res) {
        reqCount++;
		var app_info = process.env.VCAP_APPLICATION != null ? JSON.parse(process.env.VCAP_APPLICATION) : JSON.parse('{ }');
        console.log('App Info: ' + JSON.stringify(app_info));
        var limits = app_info.limits;
        var services = process.env.VCAP_SERVICES != null ? process.env.VCAP_SERVICES : JSON.parse('{ }');

		res.render('index', {
            app_info: app_info,
            limits: limits,
            services: services,
            reqCount: reqCount,
            address: process.env.ADDRESS
        });
	});

};
