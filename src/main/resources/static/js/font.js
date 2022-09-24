function saveFont(id) {
    let linkFont = document.getElementById('input_link' + id).value
    let nameFont = document.getElementById('input_name' + id).value
    let color = document.getElementById('input_color' + id).value

    let fontLint = {
        'id': id,
        'fontName': nameFont,
        'fontAddress': linkFont,
        'color': color
    }

    $.ajax({
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        url: 'font/save-font',
        data: JSON.stringify(fontLint),
    });
    setTimeout(() => {
        location.reload()
    }, 100);
}

fontNumbers()

function fontNumbers(){
    let fontAddress, fontName, color;

    $.ajax({
        type: 'POST',
        url: 'font/get-font-numbers',
        async: false,
        success: function (request) {
            fontAddress = request[0]
            fontName = request[1]
            color = request[2]
        }
    });

    let style = document.createElement('style');
    style.type = 'text/css';
    style.innerHTML = '@import url(' + fontAddress + ');\n' +
        '        .font_numbers {\n' +
        '             font-family: ' + fontName  + ';\n' +
        '              color: ' + color + '}';
    document.getElementsByTagName('head')[0].appendChild(style);

    document.getElementById('number').className = 'font_numbers fs-4';
}

