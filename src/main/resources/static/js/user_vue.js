const userTopicList = new Vue({
    el: "#my-topics",
    data: {
        topics: [],
        page: 0,
    },
    methods: {
        loadTopics(url,obj) {
            axios.get(url).then(
                function (topics) {
                    $("#container_body_right_item").append($(topics.data))
                    $(obj).remove()
                }
            )
        }
    }
})