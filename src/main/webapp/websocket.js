var wsUri = "ws://localhost:8080/nb_projects/websocket";
var websocket = new WebSocket(wsUri);
var output = document.getElementById("output");
var output2 = document.getElementById("output2");
output2.innerHTML += "Тест Тест Тест";

websocket.onopen = function (evt) {
    onOpen(evt);
};
websocket.onmessage = function (evt) {
    setText(evt);
};

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}
function onOpen(evt) {
    writeToScreen('<span style="color: green;">Connected to ' + wsUri + '</span> ');
}
function sendText(json) {
    console.log("sending text: " + json);
    websocket.send(json);
}
