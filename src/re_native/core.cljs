(ns re-native.core
  (:require [reagent.core :as r]
            [re-frame.core :as re]))

(def react-native (js/require "react-native"))
(def SwipeableListViewDataSource (js/require "react-native/Libraries/Experimental/SwipeableRow/SwipeableListViewDataSource"))

(def AppRegistry (.-AppRegistry react-native))

(def registerComponentAppRegistry (.-registerComponent AppRegistry))
(def PermissionsAndroid (.-PermissionsAndroid react-native))
(def Linking (.-Linking react-native))
(def RefreshControl (.-RefreshControl react-native))
(def StatusBar (.-StatusBar react-native))
(def Button (.-Button react-native))
(def ListView (.-ListView react-native))
(def KeyboardAvoidingView (.-KeyboardAvoidingView react-native))
(def Keyboard (.-Keyboard react-native))
(def addListenerKeyboard (.-addListener Keyboard))
(def Slider (.-Slider react-native))
(def Text (.-Text react-native))
(def TextInput (.-TextInput react-native))
(def View (.-View react-native))
(def Image (.-Image react-native))
(def ScrollView (.-ScrollView react-native))
(def ActivityIndicator (.-ActivityIndicator react-native))
(def TouchableHighlight (.-TouchableHighlight react-native))
(def TouchableWithoutFeedback (.-TouchableWithoutFeedback react-native))
(def TouchableOpacity (.-TouchableOpacity react-native))
(def Animated (.-Animated react-native))
(def AnimatedView (.-View Animated))
(def AnimatedImage (.-Image Animated))
(def AnimatedValue (.-Value Animated))
(def Easing (.-Easing react-native))
(def quadEasing (.-quad Easing))
(def inOutEasing (.inOut Easing))
(def springAnimated (.-spring Animated))
(def timingAnimated (.-timing Animated))
(def decayAnimated (.-decay Animated))
(def parallelAnimated (.-parallel Animated))
(def sequenceAnimated (.-sequence Animated))
(def AsyncStorage (.-AsyncStorage react-native))
(def getItemAsyncStorage (.-getItem AsyncStorage))
(def setItemAsyncStorage (.-setItem AsyncStorage))
(def removeItemAsyncStorage (.-removeItem AsyncStorage))

(def SwipeableListView (.-SwipeableListView react-native))
(def Modal (.-Modal react-native))
(def Alert (.-Alert react-native))
(def alertAlert (.-alert Alert))
(def DataSource (.-DataSource ListView))
(def getNewDataSourceSwipeableListView (.-getNewDataSource SwipeableListView))

(def Platform (.-Platform react-native))
(def OS (.-OS Platform))

(defn interpolateAnimatedValue [animated-value args]
  (.interpolate animated-value args))

(defn startAnimated [a complete-fn]
  (if complete-fn
   (.start a complete-fn)
   (.start a)))

(defn getRowCountDataSource [ds]
  (.getRowCount (.-_dataSource ds)))

(defn cloneWithRowsAndSectionsDataSource [ds rows sections]
  (.cloneWithRowsAndSections ds rows sections))

(assert react-native)
(assert SwipeableListViewDataSource)
(assert AppRegistry)
(assert Linking)
(assert registerComponentAppRegistry)
(assert RefreshControl)
(assert StatusBar)
(assert Button)
(assert ListView)
(assert KeyboardAvoidingView)
(assert Keyboard)
(assert addListenerKeyboard)
(assert Slider)
(assert Text)
(assert TextInput)
(assert View)
(assert Image)
(assert ScrollView)
(assert ActivityIndicator)
(assert TouchableHighlight)
(assert TouchableWithoutFeedback)
(assert TouchableOpacity)
(assert Animated)
(assert AnimatedView)
(assert AnimatedImage)
(assert AnimatedValue)
(assert Easing)
(assert quadEasing)
(assert inOutEasing)
(assert springAnimated)
(assert timingAnimated)
(assert decayAnimated)
(assert parallelAnimated)
(assert sequenceAnimated)
(assert AsyncStorage)
(assert getItemAsyncStorage)
(assert setItemAsyncStorage)
(assert removeItemAsyncStorage)
(assert SwipeableListView)
(assert Modal)
(assert Alert)
(assert alertAlert)
(assert DataSource)
(assert getNewDataSourceSwipeableListView)
(assert Platform)
(assert OS)

(def action-sheet-provider (r/adapt-react-class ActionSheetProvider))
(def action-sheet (r/adapt-react-class ActionSheet))
(def keyboard-aware-scroll-view (r/adapt-react-class KeyboardAwareScrollView))
(def blur-view (r/adapt-react-class BlurView))
(def vibrancy-view (r/adapt-react-class VibrancyView))
(def touchable (r/adapt-react-class TouchableNativeFeedbackSafe))
(def date-picker (r/adapt-react-class DatePicker))
(def material-icon (r/adapt-react-class MaterialIcons))
(def font-awesome (r/adapt-react-class FontAwesome))
(def slider (r/adapt-react-class Slider))
(def text (r/adapt-react-class Text))
(def text-input (r/adapt-react-class TextInput))
(def view (r/adapt-react-class View))
(def image (r/adapt-react-class Image))
(def scroll-view (r/adapt-react-class ScrollView))
(def touchable-highlight (r/adapt-react-class TouchableHighlight))
(def touchable-opacity (r/adapt-react-class TouchableOpacity))
(def modal (r/adapt-react-class Modal))
(def refresh-control (r/adapt-react-class RefreshControl))
(def status-bar (r/adapt-react-class StatusBar))
(def button (r/adapt-react-class Button))
(def keyboard-avoiding-view (r/adapt-react-class KeyboardAvoidingView))
(def animated-view (r/adapt-react-class AnimatedView))
(def animated-image (r/adapt-react-class AnimatedImage))
(def swipeable-list-view (r/adapt-react-class SwipeableListView))
(def os OS)

(defn request-permission [permission title message granted not-granted]
  (let [p (condp = permission
                 :write-external-storage (-> PermissionsAndroid .-PERMISSIONS .-WRITE_EXTERNAL_STORAGE))]
    (.then (.request PermissionsAndroid p
                                        (clj->js {:title title
                                                  :message message}))
           (fn request-permission-fn [granted2]
             (if (= granted2 (-> PermissionsAndroid .-RESULTS .-GRANTED))
               (granted)
               (not-granted))))))

(defn linking-open-url [url]
  (.openURL Linking url))

(defn async-storage-get-item [key callback-fn]
  (getItemAsyncStorage key callback-fn))

(defn async-storage-set-item [key value callback-fn]
  (setItemAsyncStorage key value callback-fn))

(defn async-storage-remove-item [key callback-fn]
  (removeItemAsyncStorage key callback-fn))

(defn app-registry-register-component [a b]
  (registerComponentAppRegistry a b))
