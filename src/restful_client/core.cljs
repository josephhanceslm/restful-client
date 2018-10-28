(ns restful-client.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as reagent :refer [atom]]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]))

(enable-console-print!)

(println "This text is printed from src/restful-client/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Production Status"}))

(defn error-handler [error]
  (js/alert error))

(defn call-web-service-post
  "Call a web service using POST"
  [web-service-url remote-url]
  (go (let [response (<! (http/post web-service-url
              {:with-credentials? false
               :json-params {
               :url remote-url}}))]
    (swap! app-state assoc :response response))))

(defn call-web-service-get
  "Call a web service"
  [web-service-url]
  (go (let [response (<! (http/get web-service-url
                                 { :with-credentials? false }))]
      ;(prn (:error-code response) (:error-text response))
      ; Prints :no-error "" for localhost URL
      ; Prints :http-error ":" " [0]" for DEV server URL
      ;;(cond (= (:status response) 200)
      ;;  (js/alert (:hello (:body response)))
      ;;:else 
      ;;  (js/alert "ERROR:" (:error-code response) (:error-text response))))
  (swap! app-state assoc :response response))))

(defn hello-world []
[:div
 [:h1 (:text @app-state)]
 [:h2 "Current: " (-> (:response @app-state) :body :Body :Data :PAC :Values :1)
 [:span " W"]]
 [:h2 "Today: " (-> (:response @app-state) :body :Body :Data :DAY_ENERGY :Values :1)
 [:span " Wh"]]
])

(reagent/render-component [hello-world]
                        (. js/document (getElementById "app")))

(defn on-js-reload []
;; optionally touch your app-state to force rerendering depending on
;; your application
;; (swap! app-state update-in [:__figwheel_counter] inc)
)

;(call-web-service-get "http://localhost:3000")
(defn update-values []
  (call-web-service-post "http://localhost:3000/post" "http://10.0.0.15/solar_api/v1/GetInverterRealtimeData.cgi?Scope=System"))

(defonce interval (atom 0))

(defn start []
  (reset! interval (js/setInterval #(update-values) 5000)))

(start)