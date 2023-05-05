export function transformData(data) {
    const links = data.map(d => ({ ...d.rel, source: d.from.elementId, target: d.to.elementId }));
    const nodes = [
        ...data.map(d => ({ ...d.from, id: d.from.elementId.toString() })),
        ...data.map(d => ({ ...d.to, id: d.to.elementId.toString() }))
    ];
    const nodeMap = new Map(nodes.map(d => [d.id, d]));
    // eslint-disable-next-line no-unused-vars
    const linkMap = new Map(links.map(d => [d.id, d]));
    links.forEach(d => {
        d.source = nodeMap.get(d.source);
        d.target = nodeMap.get(d.target);
    });
    return { nodes, links };
}