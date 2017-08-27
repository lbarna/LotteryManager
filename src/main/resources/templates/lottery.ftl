<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lottery manager</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
    <h1>Welcome to Lottery Manager!</h2>

    <h1>Previous winner numbers</h2>

    <table>
        <tr>
            <th>Year</th>
            <th>Date</th>
            <th>Prize</th>
            <th>Hit</th>
            <th>Numbers</th>
        </tr>
        <#list winnerNumbers as number>
            <tr>
                <td>${(number.year)!""}</td>
                <td>${(number.date)!}</td>
                <td>${(number.prize)!"0 Ft"}</td>
                <td>${(number.winnersCount)!}</td>
                <td>${number.numbers[0]},${number.numbers[1]},${number.numbers[2]},${number.numbers[3]},${number.numbers[4]}</td>
            </tr>
        </#list>
    <table>
</body>
</html>