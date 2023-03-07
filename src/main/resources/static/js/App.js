// import React from "react";
// import { useState, useEffect } from "react";
import axios from "axios";



//
//
// export default function App() {
//     // ...
//     const [data, setData] = useState(null);
//     const [loading, setLoading] = useState(true);
//     const [error, setError] = useState(null);
//
//     useEffect(() => {
//         fetch(`https://openlibrary.org/search.json?q=harry+potter`)
//             .then((response) => {
//                 // ...
//             })
//             .then((actualData) => {
//                 setData(actualData);
//                 setError(null);
//             })
//             .catch((err) => {
//                 setError(err.message);
//                 setData(null);
//             })
//             .finally(() => {
//                 setLoading(false);
//             });
//     }, []);
//
//     return (
//         <div className="App">
//             <h1>API Posts</h1>
//             {loading && <div>A moment please...</div>}
//             {error && (
//                 <div>{`There is a problem fetching the post data - ${error}`}</div>
//             )}
//             <ul>
//                 {data &&
//                     data.map(({ id, title }) => (
//                         <li key={id}>
//                             <h3>{title}</h3>
//                         </li>
//                     ))}
//             </ul>
//         </div>
//     );
// }


export default function App() {
    // ...
    const [data, setData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const getData = async () => {
            try {
                const response = await axios.get(
                    `https://openlibrary.org/search.json?q=harry+potter`
                );
                setData(response.data);
                setError(null);
            } catch (err) {
                setError(err.message);
                setData(null);
            } finally {
                setLoading(false);
            }
        };
        console.log(getData());
    }, []);
}