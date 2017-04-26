# re-native

A Clojurescript library for [react-native](https://github.com/facebook/react-native)
Meant for use with reagent and re-frame

Tracking  | Artifact
----------|---------|
`v0.41.2` | `[re-native "0.0.1-SNAPSHOT"]`

## npm

```
npm i react-native@v0.41.2 --save
```

## re-natal

```sh
re-natal use-component "react-native/Libraries/Experimental/SwipeableRow/SwipeableListViewDataSource"
```

## example

```clojure
(defn goal-list [{:keys [params]}]
  (let [goals (re/subscribe [:goals])]
    [rn/view {:style {:flex 1}}))

(defn goal-list-screen []
  (wrap-navigation
   goal-list
   {:title #(str "TITLE")
    :left #(r/as-element [ui/bar-button-drawer])
    :right #(r/as-element [ui/bar-button-drawer])})))

(defn app-root []
  (let [stack-router {:goal-list {:screen (goal-list-screen)}
                      :goal-edit {:screen (goal-edit-screen)}}

        navigator-config {:initialRouteName "goal-list"
                          :headerMode "screen"}

        sn (make-stack-navigator stack-router navigator-config)

        index-comp (fn index-comp-r [props]
                     [sn {:ref (fn sn-ref [r] (reset! stack-navigator-instance-ratom r))}])

        index-screen (wrap-navigation
                       index-comp
                       {:drawer (fn index-screen-drawer-fn []
                                  (clj->js {:label "Index"}))})

        drawer-router {:Index {:screen index-screen}}

        drawer-navigator-config {:drawerWidth      300
                                 :drawerPosition   "left"
                                 :initialRouteName "Index"
                                 :contentComponent (fn content-component-fn [navigation]
                                                     (r/as-element [drawer-content]))}

        drawer-nav (make-drawer-navigator drawer-router drawer-navigator-config)

        dn (r/adapt-react-class drawer-nav)]

   [rn/view {:style {:flex 1}}
     [dn {:ref (fn dn-ref [r] (reset! drawer-navigator-instance-ratom r))}]]))
```
