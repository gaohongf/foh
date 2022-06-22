const souSuo = new Vue({
    el: ".search",
    data: {
        key: '',
        topics: [],
        isHover: false
    },
    methods: {
        sou(obj) {
            $(obj).unbind()
            let dataUrl = $(obj).attr("data-url");
            let val = $(obj).val();
            souSuo.topics = []
            if (val.trim() === '') {
                $(obj).on("input", function () {
                    souSuo.sou(obj)
                })
                return;
            }
            axios.get(dataUrl + val).then(function (msg) {
                $(obj).on("input", function () {
                    souSuo.sou(obj)
                })
                souSuo.topics = souSuo.topics.concat(msg.data)
            })
        },
        linkTopic(obj) {
            let url = $(obj).attr("data-url")
            let id = $(obj).siblings("input:hidden").val()
            $(obj).attr("href", url + "/" + id);
            return true;
        },
        toHide(flag,obj) {
            if (flag) {
                if (!this.isHover) {
                    $(".text_item").hide();
                }
            } else {
                this.sou(obj)
                $(".text_item").show();
            }
        }
    }
})