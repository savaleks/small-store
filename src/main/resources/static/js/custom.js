
$(document).ready(function () {

// code for jquery dataTable
    var products = [
        ['1', 'qwerty'],
        ['2', 'sdfsdty'],
        ['3', 'qwfsdfsy'],
        ['4', 'qwertdfy'],
        ['5', 'qfsdfty']
    ];

    var $table = $('#productListTable');
    if ($table.length){
        // console.log('Inside the table');

        $table.DataTable({
            lengthMenu: [[10, 20, 50, -1], ['10', '20', '50', 'ALL']],
            pageLength: 10,
            data: products
        });
    }
});
