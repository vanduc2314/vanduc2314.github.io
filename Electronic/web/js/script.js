/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function () {
    $(document).on("click", ".status-btn", function (e) {
        $.ajax({
            url: "DetailOrder",
            method: "post",
            data: {pid:'${param.pid}',
                bstatus:'trading'},
            success: function (response) {
                
            }
        });
    }
    );
});

