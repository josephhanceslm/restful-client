# restful-client

Example of a simple SPA in ClojureScript that queries data from a RESTful web service and displays that on the page.

## Overview

I'm just getting started with Clojure and it's my experience that the best way to become fluent in a new language, in this case a new paradigm, is to force yourself to do real work with it.  I know some spend considerable time reading books but, for me at least, that is no substitute for struggling through it.

I have a PV installation at my home and the inverter is a [Fronius Primo](http://www.fronius.com/en/photovoltaics/products/all-products/inverters/fronius-primo/fronius-primo-3-0-1) which has a nice JSON-based [API](http://www.fronius.com/~/downloads/Solar%20Energy/Operating%20Instructions/42%2C0410%2C2012.pdf) that provides significant details about the system operation both current and over time.  My intent was to write a ClojureScript-based page to display information read from the inverter but, of course, the inverter's API doesn't support CORS and so the ClojureScript pages can't make web service calls to it directly.  See my other repo for details about the [backend service](https://github.com/josephhanceslm/restful-clojure) but this repo deals with the web page itself

## Setup

To get an interactive development environment run:

    lein figwheel

and open your browser at [localhost:3449](http://localhost:3449/).
This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

To clean all compiled files:

    lein clean

To create a production build run:

    lein do clean, cljsbuild once min

And open your browser in `resources/public/index.html`. You will not
get live reloading, nor a REPL. 

## License

Copyright © 2018 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
