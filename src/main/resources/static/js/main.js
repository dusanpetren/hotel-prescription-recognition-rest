const HTTP_STATUS_REDIRECT = "PERMANENT_REDIRECT";
const HTTP_STATUS_OK = "OK";

var stompClient = null;
var latestGeneratedCode = null;

function initializeAndSubscripeWebSocketOnMobile() {
    console.log("connect with stomp: " + (stompClient != null));
    if (stompClient === null) {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            joinWebSocketSession();
        });
    }
}

function initializeAndSubscripeWebSocket() {
    console.log("connect with stomp: " + (stompClient != null));
    if (stompClient === null) {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/socket/prescription/' + latestGeneratedCode, function (msFromWS) {
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
    console.log("joinWebSocketSession");
    var websocketDestination = $('#generatedCode').text();
    stompClient.send("/api/join/" + websocketDestination, {}, JSON.stringify({'imageBase64': name}));
}

function sendName() {
    var name = $("#name").val()
    var websocketDestination = $('#generatedCode').text();
    stompClient.send("/api/add/" + websocketDestination, {}, JSON.stringify({'imageBase64': name}));
}

function startIndex() {
    $.ajax({
        url: '/code/generate',
        type: 'GET',
        success: function (response) {
            latestGeneratedCode = response;
            console.log("generated Id: " + latestGeneratedCode);
            $('#qrcode').qrcode("https://presreco-rest.herokuapp.com/web/mobile/`" + latestGeneratedCode);
            initializeAndSubscripeWebSocket()
        },
        error: function (request, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
};

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
    window.location.href = "/web/code/" + latestGeneratedCode;
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message['body'] + "</td></tr>");
}

function previewFile() {
    const preview = document.querySelector('img');
    const file = document.querySelector('input[type=file]').files[0];
    const reader = new FileReader();

    reader.addEventListener("load", function () {
        // convert image file to base64 string
        preview.src = reader.result;
        base64 = preview.src;
        console.log("preview.src: " + preview.src);
        console.log("base64: " + base64);
    }, false);

    if (file) {
        console.log("file: " + reader.readAsDataURL(file));
        reader.readAsDataURL(file);
    }
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