$(document).ready(function() {
    $("form").submit(function(e) {
        e.preventDefault();
    });
    $("#submit").click(function(event) {
        $.ajax({
            url: 'http://192.168.1.118:8080/rememberthename/api/customer/',
            type: 'POST',
            data: JSON.stringify({
                firstName: $('#firstName').val(),
                email: $('#email').val(),
                phone: $('#phone').val(),
                numberOfCustomer: $('#numberCostumer').val(),
                notes: $('#notes').val()
            }),
            async: true,
            contentType: 'application/json;charset=UTF-8',
            success: successCallback,
            error: errorCallback
        });
    });

    function errorCallback(request, status, error) {
        console.log(status + " error " + error.message)
    }

    function redirect() {
        window.location = "thanks.html"
    }

    function successCallback(response) {
        redirect();
    }
})