import axios from 'axios'
function apiGetAllEntity(theme) {
    return axios.get('http://10.99.104.191:8080/label',{params:theme})
}

export default apiGetAllEntity

