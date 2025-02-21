$(document).ready(function() {
    callAjax();
    callImage();
});

function callAjax() {

    $.ajax({
        async: false,
        type: "GET",
        url: "https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json",
        data: {
            key: "353929f4ba4a2319493383369236e6c8",
            targetDt: "20250218"
        },
        dataType: "json",
        success: function (result){
            console.log("success");
            let movieList = result.boxOfficeResult.dailyBoxOfficeList;
            $.each(movieList, function(index, item){
                let movieName = item.movieNm;
                let movieRank = item.rank;
                let audienceCount = item.audiCnt;
                let openDate = item.openDt;
                let movieRow = "<tr><td>" + movieRank + "</td><td></td><td>" + movieName + "</td><td>" + audienceCount + "</td><td>" + openDate + "</td><td><input type='button' value='삭제'></td></tr>";
                $('#boxOfficeList').append(movieRow);
            });
        },
        error: function (){
            alert("error");
        }
    })
}

function callImage(){
    let movies = $('#boxOfficeList > tr');
    movies.each(function (index, item){
        console.log(index);
        console.log(item);
        let movieName = $(item).find('td:nth-child(3)').text().trim();
        $.ajax({
            async: false,
            type: "GET",
            url: "https://dapi.kakao.com/v2/search/image",
            data: {
                query: movieName + " 포스터",
                size: 1
            },
            dataType: "json",
            success: function (result){
                // let imageUrl = result.documents[0].image_url;
                let imageUrl = result.documents[0].thumbnail_url;
                // imageUrl = imageUrl.replace("https://", "http://")
                $(item).find('td:nth-child(2)').html('<img src="' + imageUrl + '">');
            },
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Authorization", "KakaoAK f43e6c1904a8d0995ed807661eb1a58d");
            },
            error: function (){
                alert("error");
            }
        })
    });
}