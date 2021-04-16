$(document).ready(function () {
  $(".filter button").click(function () {
    let tab = $(this).data("filter");
    $(".gal a").each(function () {
      if ($(this).hasClass(tab)) {
        $(this).fadeIn();
      } else {
        $(this).fadeOut();
      }
    });
    return false;
  });
  var countDownDate = new Date("Apr 20,2021 12:30:00").getTime();
  var x = setInterval(() => {
    var now = new Date().getTime();
    var distance = countDownDate - now;
    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor(
      (distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
    );
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

    $("#day").html(days);
    $("#minutes").html(minutes);
    $("#hours").html(hours);
    $("#seconds").html(seconds);
    if (distance < 0) {
      clearInterval(x);
      $(".join").html("hết hạn");
      $(".countdown").addClass("count");
      $(".join").addClass("expire").removeClass("join");
    }
  }, 1000);

  $(".vote").click(function () {
    let row = $(this).closest("tr");
    let point = row.find(".point").html();
    point++;
    $(row.find(".point")).html(point);
  });

  $(".search-butt").click(function () {
    let value = $(".input-group input").val();
    if (value != 0) {
      let arr = [];
      $(".table tr").each(function () {
        let name = $(this).find("td").eq(1).html();
        arr.push(name);
      });
      var result = arr.find((e) => e == value);
      $("tbody tr").each(function () {
        if ($(this).find("td").eq(1).html() != result) {
          let row = $(this).closest("tr");
          $(row).addClass("hide");
        }
      });
    } else {
      $("tr").removeClass("hide");
    }
  });
  $(".join").click(function () {
    let name = $(".name").val();
    let email = $(".email").val();
    let src_pic = $(".file").val();
    let check = $('input[class="form-check-input"]:checked').length;
    if (
      name.length > 0 &&
      email.length > 0 &&
      src_pic.length > 0 &&
      check > 0
    ) {
      $("tbody").append(
        `<tr>
    <th scope="row">1</th>
    <td><a href="` +
          src_pic`"><img src="` +
          src_pic`" class="img-fluid"></a></td>
    <td>` +
          name +
          `</td>
    <td>` +
          email +
          `</td>
    <td> <p class="point">0</p> </td>
    <td><button class="vote"><span class="iconify" data-icon="bx:bx-like" data-inline="false"></span></button></td>
</tr>`
      );
      $("#exampleModal").modal("hide");
    }
  });
    var width = window.screen.width;
    if (width < 550) {
      $(".icon").removeClass("col-4");
      $(".inf").removeClass("col-4 mt-3");
      $(".fl").removeClass("col-4");
      $(".event_img").removeClass("col-4");
      $("stuff").removeClass("col-2");
      $(".event_content").removeClass("col-5");
      $(".event_content").css({ padding: "0 16px", "font-size": "1rem" });
    } else {
      $(".icon").addClass("col-4");
      $(".inf").addClass("col-4");
      $(".fl").addClass("col-4");
      $(".event_img").addClass("col-4");
      $(".stuff").addClass("col-2");
      $(".event_content").addClass("col-5");
      $(".event").css("padding", "0");
    }
  var countDownDate = new Date("Apr 20,2021 12:30:00").getTime();
  var x = setInterval(() => {
    var now = new Date().getTime();
    var distance = countDownDate - now;
    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor(
      (distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
    );
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

    $(".count-down").html(
      days + " ngày " + hours + " giờ " + minutes + " phút " + seconds + " giây"
    );
    if (distance < 0) {
      clearInterval(x);
      $(".join").html("hết hạn");
      $(".countdown").addClass("count");
      $(".join").addClass("expire").removeClass("join");
    }
  }, 1000);

  $("tr td a").fancybox({
    openEffect: "none",
    closeEffect: "none",
    helpers: {
      title: {
        type: "outside",
      },
    },
  });
  
  $(".gal a").fancybox({
    openEffect: "none",
    closeEffect: "none",
    helpers: {
      title: {
        type: "outside",
      },
    },
  });

});
