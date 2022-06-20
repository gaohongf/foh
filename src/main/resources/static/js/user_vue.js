const userTopicList = new Vue({
    el: "#my-topics",
    methods: {
        loadTopics(url, obj) {
            $(obj).unbind();
            axios.get(url).then(
                function (topics) {
                    $(obj).text("加载完成")
                    $(obj).slideUp(200, function () {
                        $(this).remove()
                    })
                    let $topics = $(topics.data).css({'top': '300px', 'opacity': '0'})
                    $("#container_body_right_item").append($topics)
                    $topics.find(".list_topic_type").each(function (e, item) {
                        $(item).css({'border-color': userTopicList.getAnColor()})
                    })
                    $topics.filter(".container_body_right_item_list").animate(
                        {'top': '0', 'opacity': '1'}, 800, function () {
                            $topics.filter(".loadOther").css({cursor: 'pointer'})
                            $topics.filter(".loadOther").animate({'opacity': '1'}, 300)
                        })
                }
            )
        },
        loadClick(url, obj) {
            /*过河拆桥*/
            $(obj).removeAttr('onclick')
            /*添加新点击事件*/
            $(obj).click(function () {
                userTopicList.loadTopics(url, obj)
            }).click().text("加载中...")
        }, getAnColor() {
            const box = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F']
            let newColor = "#"
            for (let i = 0; i < 6; i++) {
                newColor += box[Math.floor(Math.random() * 16)]
            }
            return newColor
        }
    }
})

const userFavAndHistory = new Vue({
    el: '#container_body_left',
    data: {
        page: 0
    },
    methods: {
        loadFavData(url, obj) {
            $(obj).unbind()
            $(obj).text("加载中...")
            let split = url.split('/');
            let newUrl = ''
            for (let i = 0; i < split.length - 1; i++) {
                newUrl += (split[i] + '/')
            }
            newUrl += parseInt(split[split.length - 1]) + 1
            axios.get(url).then(
                function (pages) {
                    $(obj).text("加载完成").remove()
                    let $fav = $(pages.data);
                    $("#favorites-list").append($fav)
                    $fav.filter(".loadOther").click(function () {
                        userFavAndHistory.loadFavData(newUrl, this)
                    })
                }
            )
        }
    }
})