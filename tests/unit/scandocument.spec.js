import { shallowMount } from '@vue/test-utils'
import ScanDocument from '@/components/ScanDocument.vue'

describe('ScanDocument.vue', () => {
  it('renders chatgpt response after fetch', () => {
    const msg = 'succesfully scanned document'
    const wrapper = shallowMount(ScanDocument, {
      data() {
        return {
          summary: msg
        }
      }
    })
    expect(wrapper.text()).toMatch(msg)
  })
})
