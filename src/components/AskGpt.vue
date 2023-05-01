<script>

export default {
    name: 'AskGpt',
    Components: {

    },
    
    methods: {
         async postApiRequest(input) {
            const endpoint = 'https://api.openai.com/v1/chat/completions'
            const openAiKey = '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'
            const preQuestion = 'Das folgende Dokument ist von einem Hund. Schreibe eine kurze Überschrift nach <Überschrift> fasse das Dokument in <Inhalt> zusammen und falls es ein ärztliches Dokument ist, analysiere die Werte unter <Analyse>'

            const myHeaders = new Headers()
            myHeaders.append('Authorization', 'Bearer ' + openAiKey)
            myHeaders.append('Content-Type', 'application/json')

            const raw = JSON.stringify({
                model: "gpt-3.5-turbo",
                messages: [{ "role": "user", "content": preQuestion + input }],
                temperature: 0.2
            })

            const requestOptions = {
                method: 'POST',
                body: raw,
                headers: myHeaders,
                redirect: 'follow'
          }

            this.fetchApiResponse(endpoint, requestOptions).then(async response => {
                console.log("Schritt 2: " + response)
                return response;
            })
        },

        async fetchApiResponse(endpoint, requestOptions) {
            let output = ''

            fetch(endpoint, requestOptions)
                .then(response => response.text())
                .then(async result => {
                    let parsedObj = JSON.parse(result);
                    let content = parsedObj.choices[0].message.content;
                    output = content;
                    console.log("Schritt 1" + output)
                    return output
                })
                .catch(error => console.log('error', error))
        }
    }
}

</script>