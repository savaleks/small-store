
// DataTables
$(document).ready(function () {

    // the csrf token

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        if (token.length > 0 && header.length > 0) {
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        }
    });

    // product management

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
            {data: "quantity", mRender: function (data, type, row) {
                    if (data < 1){
                        return '<span style="color:red;">Out of Stock</span>';
                    }
                    return data;
                }},
            {data: "id", mRender: function (data, type, row) {
                    var str = '';
                    str += '<a href="/show/'+data+'/product" class="btn btn-primary btn-sm">View</a>';
                    str += '&#160;';

                    if (row.quantity < 1){
                        str += '<a href="javascript:void(0)" class="btn btn-success btn-sm disabled">Add to Cart</a>';
                    } else {
                        str += '<a href="/cart/add/'+data+'/product" class="btn btn-success btn-sm">Add to Cart</a>';
                    }
                    return str;
                }}
        ]
    });

    //dismissing the alert after 3 seconds

    var $alert = $('.alert');
        if ($alert.length){
            setTimeout(function () {
                $alert.fadeOut('slow');
            }, 3000)
        }

    // bootbox for activate and deactivate button

    $('.switch input[type="checkbox"]').on('change', function () {
       var checkbox = $(this);
       var checked = checkbox.prop('checked');
       var dMsg = (checked)?'Do you want activate the product?':'Do you want deactivate the product?';
       var value = checkbox.prop('value');

       bootbox.confirm({
          size: 'small',
          title: 'Product Activation and Deactivation',
          message: dMsg,
          callback: function (confirmed) {
              if (confirmed){
                  bootbox.alert({
                      size: 'small',
                      title: 'Information',
                      message: 'You want perform operation on product ' + value
                  });
              } else {
                  checkbox.prop('checked', !checked);
              }
          }
       });
    });

    //====================

    //===================

    // DataTable for admin

    var jsonUrl = /*[[@{/json/data/admin/all/products}]]*/ '/json/data/admin/all/products';

    var adminProductsTable = $('#adminProductsTable').DataTable({
        lengthMenu: [[10, 20, 50, -1], ['10', '20', '50', 'ALL']],
        pageLength: 10,
        ajax: {url: jsonUrl, dataSrc: ''},
        columns:[
            {data: "id"},
            {data: "code", mRender: function (data, type, row) {
                    return '<img src="/images/'+data+'.jpg" class="adminDataTableImg"/>'
                }},
            {data: "name"},
            {data: "brand"},
            {data: "quantity", mRender: function (data, type, row) {
                    if (data < 1){
                        return '<span style="color:red;">Out of Stock</span>';
                    }
                    return data;
                }},
            {data: "unitPrice", mRender: function (data, type, row) {
                    return '&#8364; ' + data
                }},
            {data: "active", bSortable: false, mRender: function (data, type, row) {
                    var str = '';
                    str += '<label class="switch">';
                    if (data){
                        str += '<input type="checkbox" checked="checked" value="'+row.id+'">';
                    }
                    else {
                        str += '<input type="checkbox" value="'+row.id+'">';
                    }
                    str += '<div class="slider"></div></label>';
                    return str;
                }
                },
            {data: 'id', bSortable: false, mRender: function (data, type, row) {
                    var str = '';
                    str += '<a href="/manage/'+data+'/product" class="btn btn-link">';
                    str += '<i class="fa fa-pencil-square-o fa-2x"></i></a>';
                    return str;
                }}
        ],

        initComplete: function () {
            var api = this.api();
            api.$('.switch input[type="checkbox"]').on('change', function () {
                var checkbox = $(this);
                var checked = checkbox.prop('checked');
                var dMsg = (checked)?'Do you want activate the product?':'Do you want deactivate the product?';
                var value = checkbox.prop('value');

                bootbox.confirm({
                    size: 'medium',
                    title: 'Product Activation and Deactivation',
                    message: dMsg,
                    callback: function (confirmed) {
                        if (confirmed){
                            var activationUrl = /*[[@{/manage/product/{id}/activation]]*/ '/manage/product/'+value+'/activation';
                            $.post(activationUrl, function (data) {
                                bootbox.alert({
                                    size: 'medium',
                                    title: 'Information',
                                    message: data
                                });
                            });
                        } else {
                            checkbox.prop('checked', !checked);
                        }
                    }
                });
            });
        }
    });
});
