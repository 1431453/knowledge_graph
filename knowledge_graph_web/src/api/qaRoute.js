import axios from "axios";

function apiGetAnswer(question){
    return axios.get('http://10.99.104.191:8080/search',{params:question})
}
export default apiGetAnswer;