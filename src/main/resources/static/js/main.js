const HTTP_STATUS_REDIRECT = "PERMANENT_REDIRECT";
const HTTP_STATUS_OK = "OK";

var stompClient = null;
var latestGeneratedCode = null;

var base64;

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
    connectedAlert();
    console.log("connect with stomp: " + (stompClient != null));
    if (stompClient === null) {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            var generatedCodeFromId = $('#generatedCode').text();
            var websocketDestination = generatedCodeFromId === "" ? latestGeneratedCode : generatedCodeFromId;
            console.log(websocketDestination);
            stompClient.subscribe('/socket/prescription/' + websocketDestination, function (msFromWS) {
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

function sendImageBase64ToServer(base64) {
    $.ajax({
        url: '/prescription/resolve',
        type: 'POST',
        data: JSON.stringify({'imageBase64': base64}),
        contentType: 'application/json',
        success: function (response) {
            toastr.options = {
                "closeButton": false,
                "debug": false,
                "newestOnTop": false,
                "progressBar": false,
                "positionClass": "toast-top-full-width",
                "preventDuplicates": false,
                "onclick": null,
                "showDuration": "300",
                "hideDuration": "1000",
                "timeOut": "5000",
                "extendedTimeOut": "1000",
                "showEasing": "swing",
                "hideEasing": "linear",
                "showMethod": "fadeIn",
                "hideMethod": "fadeOut"
            };
            toastr.success('Poslano!');
            resolvedMessageFromGoogle = response;
            sendToWebSocket(resolvedMessageFromGoogle);
        },
        error: function (request, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}

function startIndex() {
    $.ajax({
        url: '/code/generate',
        type: 'GET',
        success: function (response) {
            latestGeneratedCode = response;
            console.log("generated Id: " + latestGeneratedCode);
            $('#qrcode').qrcode("https://presreco-rest.herokuapp.com/web/mobile/" + latestGeneratedCode);
            initializeAndSubscripeWebSocket()
        },
        error: function (request, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}

function redirectToGeneratedId() {
    window.location.href = "/web/code/" + latestGeneratedCode;
}

function showGreeting(message) {
    $("#resolvedMessage").append("<tr><td>" + message['body'] + "</td></tr>");
}


function generateBase64fromImage() {
    base64 = base64.substring(base64.indexOf("4") + 2, base64.length);
    console.log("base64 after cut: " + base64);
    sendImageBase64ToServer(base64);
}

function sendToWebSocket(message) {
    var websocketDestination = $('#generatedCode').text();
    stompClient.send("/api/add/" + websocketDestination, {}, JSON.stringify({'message': message}));
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
        previewFile();
    }
}

function connectedAlert() {
    toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-full-width",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    toastr.success('Připojeno!');
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