var wsUri = "ws://localhost:8080/nb_projects/websocket";
var websocket = new WebSocket(wsUri);
var comments = document.getElementById("comments");
var comment = document.getElementById("comment");
var username = document.getElementById("username");

websocket.onmessage = function (evt) {
    comments.value += evt.data;
};

function sendText(txt) {
    console.log("sending text: " + txt);
    websocket.send(txt);
}

function sendComment() {
    comments.value += username.value + ": " + comment.value + "\n\n";
    websocket.send(username.value + ": " + comment.value + "\n\n");
}

