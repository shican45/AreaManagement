import request from '../utils/request';

const api = {
    findArea(data) {
        return request({
            url: '/area/find',
            method: 'post',
            data: data
        });
    },
    getAreaList(query) {
        return request({
            url: '/area/list',
            method: 'get',
            params: query
        });
    },
    addArea(data) {
        return request({
            url: '/area/add',
            method: 'post',
            data: data
        });
    },
    updateArea(data){
        return request({
            url: '/area/update',
            method: 'post',
            data: data
        });
    },
    deleteArea(data) {
        return request({
            url: '/area/delete',
            method: 'post',
            data: data
        });
    },

    getCityList(query) {
        return request({
            url: '/city/list',
            method: 'get',
            params: query
        });
    },
    addCity(data) {
        return request({
            url: '/city/add',
            method: 'post',
            data: data
        });
    },
    updateCity(query){
        return request({
            url: '/city/update',
            method: 'get',
            params: query
        });
    },
    deleteCity(data) {
        return request({
            url: '/city/delete',
            method: 'post',
            data: data
        });
    },
};

export default api;