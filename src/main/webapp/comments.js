var comments = document.getElementById("comments");
var comment = document.getElementById("comment");
var username = document.getElementById("username");

function sendComment() {
    comments.value += username.value + ": " + comment.value + "\n\n";
    websocket.send( username.value + ": " + comment.value + "\n\n");
}

function setText(evt){
    comments.value += evt.data;
}


