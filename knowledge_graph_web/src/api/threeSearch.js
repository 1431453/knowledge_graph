import axios from "axios";

function apiGetThreeSearch(question){
    return axios.get('http://10.99.104.191:8080/match',{params:question})
}
export default apiGetThreeSearch;