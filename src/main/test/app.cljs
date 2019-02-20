(ns test.app
  (:require
    ["expo" :as ex]
    ["react-native" :as rn]
    ["react" :as r]
    [fulcro.client :as fc]
    [fulcro.client.primitives :as fp :refer (defsc)]
    ))

(defonce root-ref (atom nil))

(defonce app-ref (atom nil))

(defsc Root [this props]
  {:initial-state
   (fn [p]
     {::foo "hello world"})

   :query
   [::foo]}

  (r/createElement rn/View nil
    (r/createElement rn/Text nil "Hello from Fulcro!" (pr-str props))))

(defn start
  {:dev/after-load true}
  []
  (reset! app-ref (fc/mount @app-ref Root :i-got-no-dom-node)))

(defn init [root-component]
  (reset! root-ref root-component)

  (let [app
        (fc/make-fulcro-client
          {:client-did-mount
           (fn [{:keys [reconciler] :as app}])

           :reconciler-options
           {:root-render
            (fn [node _]
              (.swapRoot root-component node))

            :root-unmount
            (fn [node]
              (js/console.log "root-unmount" node))}})]

    (reset! app-ref app)
    (start)))

