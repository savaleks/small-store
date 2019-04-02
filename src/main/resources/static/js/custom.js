
$(document).ready(function () {

    var jsonUrl = '';
        if (window.categoryId == ''){
            jsonUrl = /*[[@{/json/data/all/products}]]*/ '/json/data/all/products';
        } else {
            jsonUrl = /*[[@{/json/data/category/{id}/products}]]*/ '/json/data/category/'+window.categoryId+'/products';
        }

    var table = $('#productListTable').DataTable({
        lengthMenu: [[10, 20, 50, -1], ['10', '20', '50', 'ALL']],
        pageLength: 10,
        ajax: {url: jsonUrl, dataSrc: ''},
        columns:[
            {data: "code", mRender: function (data, type, row) {
                   return '<img src="/images/'+data+'.jpg" class="dataTableImg"/>'
                }},
            {data: "name"},
            {data: "brand"},
            {data: "unitPrice", mRender: function (data, type, row) {
                    return '&#8364; ' + data
                }},
            {data: "quantity"},
            {data: "id", mRender: function (data, type, row) {
                    var str = '';
                    str += '<a href="/show/'+data+'/product" class="btn btn-primary btn-sm">View</a>';
                    str += '&#160;';
                    str += '<a href="/cart/add/'+data+'/product" class="btn btn-success btn-sm">Add to Cart</a>';
                    return str;
                }}
        ]
    })
});
