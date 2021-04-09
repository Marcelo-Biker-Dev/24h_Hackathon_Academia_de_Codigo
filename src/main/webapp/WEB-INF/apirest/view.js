$(document).ready(function() {

    var lastNumber = window.location.href.match(/\d+$/);

    $.ajax({
        url: 'http://localhost:8080/rememberthename/api/customer/' + lastNumber,
        async: true,
        success: successCallback,
        error: errorCallback
    });

    $.ajax({
        url: 'http://localhost:8080/rememberthename/api/customer/' + lastNumber + '/account',
        async: true,
        success: successCallbackAccount,
        error: errorCallback
    });

    function successCallback(response) {

        $('#javabankTable').append('<tr></tr>')

        Object.keys(response).forEach(property => {

            if (property == "id") {
                return
            }

            $('#javabankTable tr:last').append('<td>' + response[property] + '</td>')

        })
    }

    function successCallbackAccount(response) {

        $('#javabankTableAccount').append('<tr></tr>')

        Object.keys(response).forEach(property => {

            if (property == "id") {
                return
            }

            $('#javabankTableAccount tr:last').append('<td>' + response[property].type + '</td>')
            $('#javabankTableAccount tr:last').append('<td>' + response[property].balance + '</td>')

        })


    }

    function errorCallback(request, status, error) {
        console.log("error")
    }

    function redirect() {
        window.location = "formADD.html"

    }

    function successCallbackTwo(request) {
        window.location = "XMLindexJQuery.html"
    }

})