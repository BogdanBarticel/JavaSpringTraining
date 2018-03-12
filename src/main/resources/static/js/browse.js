

    var $table = $('table');

    var order = {};

    refreshOrder();

    for(var i=0; i<prod.length; i++){
         $table.append("<tr><td>"
         +prod[i].name+"</td><td>"
         +prod[i].description+"</td><td>"
         +prod[i].price+"</td><td>"
         +stock[i]+"</td><td>"
         +"<input id='quantity"+i+"' type='number' value='0' onchange='onProdSelectionChange(this.value, " +i+ ")'></td></tr>")
    }

    function onProdSelectionChange(){

        var total = 0;
        for(var i=0; i<prod.length; i++){
          var quantity = document.getElementById('quantity'+i).value;
          var price = prod[i].price;
          total = total + quantity * price;
        }
        document.getElementById('total').innerHTML = "Total: " + total;
    }

    $.postJSON = function(url, data, callback) {
                return jQuery.ajax({
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                'type': 'POST',
                'url': url,
                'data': JSON.stringify(data),
                'dataType': 'json',
                'success': callback
                });
            };

    function buy(){

        for(var i=0; i<prod.length; i++){
          var quantity = document.getElementById('quantity'+i).value;
          if (quantity > 0) {
                order.products[prod[i].id] = quantity;
          }
        }

        $.postJSON(origin+"/create", order, function(data){
            console.log(data);
        })
        location.refresh();


    }

    function refreshOrder(){

        order =
        {
            "customer":1,
            "timeStamp":1,
            "products":
            {},
            "destination":
            {
                "country":"a",
                "city":"b",
                "county":"c",
                "street":"d"
            }
        }

    }

    onProdSelectionChange();
