const HTTP_STATUS_REDIRECT = "PERMANENT_REDIRECT";
const HTTP_STATUS_OK = "OK";

var stompClient = null;
var latestGeneratedCode = null;

function connect() {
    if (stompClient === null) {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/socket/prescription', function (msFromWS) {
                resolveMessageFromWebsocket(msFromWS);
            });
        });
    }
}

function resolveMessageFromWebsocket(messageFromWS) {
    var webSocketparsedMessage = JSON.parse(messageFromWS.body);
    var statusCode = webSocketparsedMessage['statusCode'];
    if (HTTP_STATUS_OK === statusCode) {
        console.log("OK");
        showGreeting(webSocketparsedMessage);
    } else if (HTTP_STATUS_REDIRECT === statusCode) {
        console.log("Redirected")
        redirectToGeneratedId()
    }
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function joinWebSocketSession() {
    //todo send to specific token session
    stompClient.send("/api/join", {}, JSON.stringify({'imageBase64': name}));
}

function sendName() {
    //todo send to specific token session
    var name = $("#name").val()
    stompClient.send("/api/add", {}, JSON.stringify({'imageBase64': name}));
}

$(document).ready(function () {
    $.ajax({
        url: '/code/generate',
        type: 'GET',
        success: function (response) {
            latestGeneratedCode = response;
            console.log("generated Id: " + latestGeneratedCode);
            $('#qrcode').qrcode("https://presreco-rest.herokuapp.com/web/code/" + latestGeneratedCode);
            var canvas = $('#qrcode canvas');
            connect();
        },
        error: function (request, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
});

function generateCode() {
    $.ajax({
        url: '/code/generate',
        type: 'GET',
        success: function (response) {
            latestGeneratedCode = response;
            $("canvas").get(0).remove();
            $('#qrcode').qrcode("https://presreco-rest.herokuapp.com/web/code/" + latestGeneratedCode);
            console.log("generated Id: " + latestGeneratedCode);
            return response;
        },
        error: function (request, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}

function generateBase64fromImage() {
    function toDataURL(url, callback) {
        var xhr = new XMLHttpRequest();
        xhr.onload = function () {
            var reader = new FileReader();
            reader.onloadend = function () {
                callback(reader.result);
            }
            reader.readAsDataURL(xhr.response);
        };
        xhr.open('GET', url);
        xhr.responseType = 'blob';
        xhr.send();
    }

    toDataURL('../img/hotelHandWriting', function (dataUrl) {
        console.log('RESULT:', dataUrl)
    })
}

function redirectToGeneratedId() {
    console.log("ggoing to space: " + latestGeneratedCode);
    window.location.href = "/web/code/" + latestGeneratedCode;
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message['body'] + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#load").click(function () {
        load();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
});