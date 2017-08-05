(defproject memeshot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [net.mikera/imagez "0.12.0"]
                 [clojurefx "0.0.23"]]
  :main ^:skip-aot memeshot.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
