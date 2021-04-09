function deleteCustomer() {

    var lastNumber = window.location.href.match(/\d+$/);

    console.log(lastNumber);
    $.ajax({

        url: 'http://localhost:8080/rememberthename/api/customer/' + lastNumber,
        type: 'DELETE',
        async: true,
        contentType: 'application/json;charset=UTF-8',
        success: successCallback,
        error: errorCallback,
    });


    function errorCallback(request, status, error) {
        console.log(status + " error " + error.message)
    }

    function redirect() {
        window.location = "XMLindexJQuery.html"

    }

    function successCallback(response) {
        redirect();
    }

}

deleteCustomer();