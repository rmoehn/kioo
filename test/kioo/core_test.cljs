(ns kioo.core-test
  (:require [cemerick.cljs.test :as t]
            [kioo.core :refer [content set-attr append prepend]]
            [kioo.test :refer [render-dom]]
            [goog.dom :as gdom])
  (:require-macros [kioo.core :refer [component]]
                   [cemerick.cljs.test :refer [are is deftest testing]]))


(deftest render-test
  (testing "basic render test"
    (let [comp (component "simple-div.html" {}) ]
      (is (= "<div>test</div>" (render-dom comp)))))
  (testing "content replace"
    (let [comp (component "simple-div.html"
                          {[:div] (content "success")})]
      (is (= "<div>success</div>" (render-dom comp)))))
  (testing "append test"
    (let [comp (component "simple-div.html"
                          {[:div] (append "success")})]
      ;;note that ract wraps text nodes in span tags
      ;;this is expected to be corrected soon in react but
      ;;for now this is correct
      (is (= "<div><span>test</span><span>success</span></div>"
             (render-dom comp)))))
  (testing "prepend test"
    (let [comp (component "simple-div.html"
                          {[:div] (prepend "success")})]
      (is (= "<div><span>success</span><span>test</span></div>"
             (render-dom comp)))))
  (testing "set-attr test"
    (let [comp (component "simple-div.html"
                          {[:div] (set-attr :id "success")})]
      (is (= "<div id=\"success\">test</div>"
             (render-dom comp))))))
