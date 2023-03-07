// $(document).ready(function(){
//     $('.carousel').carousel({
//         interval: 2000
//     })
// });

$(".book-bg").click(function () {
    $(this).addClass("active");
});

$("#back svg").click(function () {
    event.stopPropagation();
    $(".book-bg").removeClass("active");
});
