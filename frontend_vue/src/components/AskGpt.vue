<script>

export default {
    name: 'AskGpt',
    Components: {},
    methods: {
         async postApiRequest(input) {
            const endpoint = 'https://api.openai.com/v1/chat/completions'
            const openAiKey = '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'
            const preQuestion = 'Das folgende Dokument ist von meinem Hundearzt. Schreibe eine kurze Überschrift, fasse das Dokument zusammen und falls es ein Dokument mit Blutwerten ist, analysiere die Werte.'

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

           return await this.fetchApiResponse(endpoint, requestOptions)
        },

        async fetchApiResponse(endpoint, requestOptions) {
            return await fetch(endpoint, requestOptions)
                .then(response => response.text())
                .then(async result => {
                    let parsedObj = JSON.parse(result);
                    let output = parsedObj.choices[0].message.content;
                    return output
                })
                .catch(error => console.log('error', error))
        }
    }
}

</script>