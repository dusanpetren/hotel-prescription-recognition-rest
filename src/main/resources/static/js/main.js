var stompClient = null;
var latestGeneratedCode = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    } else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/socket/prescription', function (greeting) {
            console.log("greeting;" + greeting);
            showGreeting(greeting.body);
        });
    });
}

function load() {
    $.getJSON("/all", function (data) {
        $.each(data, function (key, val) {
            $("#greetings").append("<tr><td>" + val.id + "</td></tr>");
        })
    })
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

// function sendName() {
//     stompClient.send("/api/add/", {}, JSON.stringify({'name': $("#name").val()}));
// }

function sendName() {
    stompClient.send("/api/add/", $("#name").val());
}

$( document ).ready(function() {

    $.ajax({
        url: '/code/generate',
        type: 'GET',
        success: function (response) {
            latestGeneratedCode = response;
            console.log("generated Id: " + latestGeneratedCode);
            $('#qrcode').qrcode("https://presreco-rest.herokuapp.com/web/code/"+ latestGeneratedCode);
            var canvas = $('#qrcode canvas');
            console.log(canvas);
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
            $( "canvas").get( 0 ).remove();
            $('#qrcode').qrcode("https://presreco-rest.herokuapp.com/web/code/"+ latestGeneratedCode);
            console.log("generated Id: " + latestGeneratedCode);
            return response;
        },
        error : function(request, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}

function generateBase64fromImage() {
    function toDataURL(url, callback) {
        var xhr = new XMLHttpRequest();
        xhr.onload = function() {
            var reader = new FileReader();
            reader.onloadend = function() {
                callback(reader.result);
            }
            reader.readAsDataURL(xhr.response);
        };
        xhr.open('GET', url);
        xhr.responseType = 'blob';
        xhr.send();
    }

    toDataURL('../img/hotelHandWriting', function(dataUrl) {
        console.log('RESULT:', dataUrl)
    })
}

function redirectToGeneratedId() {
    console.log("ggoing to space: " + latestGeneratedCode);
    window.location.href = "/web/code/" + latestGeneratedCode;
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
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