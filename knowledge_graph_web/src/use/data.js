import data from './data.js';
data.nodes = [
    {id: 0, name: "Node 0"},
    {id: 1, name: "Node 1"},
    {id: 2, name: "Node 2"},
    {id: 3, name: "Node 3"},
    {id: 4, name: "Node 4"}
];
data.links = [
    {source: 0, target: 1},
    {source: 1, target: 2},
    {source: 2, target: 3},
    {source: 3, target: 4},
    {source: 4, target: 0},
    {source: 0, target: 3},
    {source: 2, target: 4}
];
export default data;