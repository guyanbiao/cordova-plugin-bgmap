/*global cordova, module*/

module.exports = {
    openMapDirection: function (url, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Bgmap", "openMapDirection", [url]);
    }
};
