(ns re-native.core-fx
  (:require [reagent.core :as r]
            [re-frame.core :as re]
            [re-native.core :as rn]))

(re/reg-fx
  :linking-open-url
  (fn [{:keys [url]}]
    (rn/linking-open-url url)))

(re/reg-fx
  :show-alert
  (fn show-alert [{:keys [title
                          message
                          options
                          on-press]
                   :or   {options  []
                          title    "EMPTY"
                          message  "EMPTY"
                          on-press [:show-alert-no-on-press]}}]
    (let [o (clj->js
              (map-indexed
                (fn [idx t]
                  #js {:text t :onPress (fn [] (re/dispatch (conj on-press idx)))})
                options))]
      (rn/alert title message o))))

(re/reg-fx
  :async-storage-get
  (fn async-storage-get-fx [{:keys [key on-success on-failure]
                             :or   {on-success [:async-storage-get-no-on-success]
                                    on-failure [:async-storage-get-no-on-failure]}}]
    (rn/async-storage-get-item key (fn async-storage-get-item-cb [err result]
                                     (if err
                                       (re/dispatch (conj on-failure err))
                                       (if (nil? result)
                                         (re/dispatch (conj on-failure :result-nil))
                                         (re/dispatch (conj on-success result))))))))

(re/reg-fx
  :async-storage-set
  (fn async-storage-set-fx [{:keys [key value on-success on-failure]
                             :or   {on-success [:async-storage-set-no-on-success]
                                    on-failure [:async-storage-set-no-on-failure]}}]
    (if (nil? value)
      (re/dispatch (conj on-failure :value-nil))
      (rn/async-storage-set-item key value (fn async-storage-set-item-cb [err]
                                             (if err
                                               (re/dispatch (conj on-failure err))
                                               (re/dispatch on-success)))))))

(re/reg-fx
  :async-storage-remove
  (fn async-storage-remove-fx [{:keys [key on-success on-failure]}]
                             :or   {on-success [:async-storage-remove-no-on-success]
                                    on-failure [:async-storage-remove-no-on-failure]}
    (rn/async-storage-remove-item key (fn async-storage-remove-item-cb [err]
                                        (if err
                                          (re/dispatch (conj on-failure err))
                                          (re/dispatch on-success))))))
