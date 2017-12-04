var init = function () {
    connectWebViewJavascriptBridge(function(bridge) {
        bridge.init(function(message, responseCallback) {
            var data = {
                'Javascript Responds': '测试中文!'
            };
            responseCallback(data);
        });
    })
};

var connectWebViewJavascriptBridge = function(callback) {
    if (window.WebViewJavascriptBridge) {
        callback(WebViewJavascriptBridge)
    } else {
        document.addEventListener(
            'WebViewJavascriptBridgeReady'
            , function() {
                callback(WebViewJavascriptBridge)
            },
            false
        );
    }
}

init();

