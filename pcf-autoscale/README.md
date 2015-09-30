=============
pcf-autoscale
=============

![](https://github.com/azwickey-pivotal/pcf-autoscale/blob/master/image.png)

Simple scaling demo.  A ruby app I've been using to demo CF lately.  Its modeled off a java app Matt Stine wrote, but I recreated it in ruby so it is a bit more lightweight.  Now that it is ruby you can easily scale to 30 40 50 instances on a modest env.  Also only takes a few seconds to upload since its so small, which is nice when you are demoing but only tethering to your phone for internet!!

I also added a few more features.  There is a "/load" endpoint that simulates load on the app.  I use this, our autoscale service, and apache bench to demonstrate autoscaling based on CPU.  E.G. --  'ab -n 10000 -c 50 http://scale-abz.cloudfoundry.dyndns.org/load'.  Its helpful to have a CLI window with watch (http://linux.about.com/library/cmd/blcmdl1_watch.htm)   "watching" the command cf app scale-demo.   You'll see instances get spun up and down.

The app also shows some of the env variables so you can talk about linux containers, isolation, service bindings, etc.  Lastly, the app also has /killSwitch endpoint that will cause the instance to fail.  Good for demoing health manager.
