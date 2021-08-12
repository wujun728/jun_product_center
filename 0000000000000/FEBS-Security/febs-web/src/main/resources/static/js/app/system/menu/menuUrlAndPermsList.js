$(function () {
    initUrlAndPermsList()
});

function initUrlAndPermsList() {
    // urls complete
    $.getJSON(ctx + "menu/urlList", function (r) {
        $('#menu-url-list').autocomplete({
            hints: r,
            keyname: 'url',
            // width: 300,
            // height: 29,
            valuename: 'url',
            showButton: false,
            onSubmit: function (text) {
                $('#menu-url').val(text);

            }
        });
        //    perms complete
        $('#menu-perms-list').autocomplete({
            hints: r,
            keyname: 'perms',
            // width: 300,
            // height: 29,
            valuename: 'perms',
            showButton: false,
            onSubmit: function (text) {
                $('#menu-perms').val(text);
            }
        });
    });


}
