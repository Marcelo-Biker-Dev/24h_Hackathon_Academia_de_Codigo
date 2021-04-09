$(document).ready(function() {

    $("form").submit(function(e) {

        e.preventDefault();
    });

    var lastNumber = window.location.href.match(/\d+$/);

    $.ajax({
        url: 'http://localhost:8080/rememberthename/api/customer/' + lastNumber,
        async: true,
        success: successCallback,
        error: errorCallback
    });

    function successCallback(response) {

        Object.keys(response).forEach(property => {

            $("#" + property).val(response[property])

        })

    }

    function errorCallback(request, status, error) {
        console.log("error")
    }

    function redirect() {
        window.location = "formADD.html"

    }

    /* POST METHOD EDIT CUSTOMER */


    $("#submit").click(function(event) {

        $.ajax({

            url: 'http://localhost:8080/rememberthename/api/customer/' + lastNumber,
            type: 'PUT',
            data: JSON.stringify({
                firstName: $('#firstName').val(),
                lastName: $('#lastName').val(),
                email: $('#email').val(),
                phone: $('#phone').val(),

            }),
            async: true,
            contentType: 'application/json;charset=UTF-8',
            success: successCallbackTwo,
            error: errorCallback

        });
    });



    function successCallbackTwo(request) {
        window.location = "XMLindexJQuery.html"
    }

})