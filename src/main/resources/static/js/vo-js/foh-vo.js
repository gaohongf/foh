/*
 * 与项目互相映射的vo对象
 * 作用主要是可以让前端有语法提示降低调用错误率
 */
function UserTopicPage(topics, hasNextPage) {
    this.topics = topics
    this.hasNextPage = hasNextPage;
}

function Topic(id,title,typeid,authorid,content,time){
    this.id = id;
    this.title = title;
    this.typeid = typeid;
    this.authorid = authorid;
    this.content = id;
    this.time = time;
}