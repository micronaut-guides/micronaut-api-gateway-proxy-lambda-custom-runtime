package petstoregraal

import io.micronaut.context.ApplicationContext
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.BlockingHttpClient
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class PetStoreControllerSpec extends Specification {

    @AutoCleanup
    @Shared
    EmbeddedServer embeddedServer = ApplicationContext.run(EmbeddedServer, [:])

    @Shared
    ApplicationContext applicationContext = embeddedServer.applicationContext

    @Shared
    HttpClient httpClient = applicationContext.createBean(HttpClient, embeddedServer.URL)

    @Shared
    BlockingHttpClient client  = httpClient.toBlocking()
    
    void "/pets returns a list of animals"() {
        when:
        HttpResponse<List<Pet>> resp = client.exchange(HttpRequest.GET('/pets'), Argument.of(List, Pet))

        then:
        resp.status() == HttpStatus.OK
        and:
        resp.body().size() > 1
    }

}
