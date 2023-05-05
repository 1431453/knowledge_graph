import neo4j from 'neo4j-driver';
const driver = neo4j.driver("bolt://10.99.104.191:7687", neo4j.auth.basic("neo4j", "123456"));
const session = driver.session();
export async function fetchNeo4jData() {
    try {
        const { records } = await session.run("MATCH (n:divereandeq)-[r:包括]->(m:divereandeq) RETURN n,r,m");
        return records.map((record) => ({
            // from: record.get('n').properties,
            // to: record.get('m').properties,
            // rel: { ...record.get('r').properties, id: record.get('r').identity.toNumber() }
            from:record.get('n'),
            to: record.get('m'),
            rel:record.get('r')
        }));
    } catch (err) {
        console.error(err);
        throw err;
    }
}