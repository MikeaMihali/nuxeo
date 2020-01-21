/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *  
 * Contributors:
 *     Salem Aouana
 */

package org.nuxeo.ecm.automation.core.operations.document;

import static org.junit.Assert.assertTrue;
import static org.nuxeo.ecm.core.blob.ColdStorageHelper.COLD_STORAGE_BEIGN_RETRIEVED_PROPERTY;
import static org.nuxeo.ecm.core.blob.ColdStorageHelper.FILE_CONTENT_PROPERTY;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.OperationException;
import org.nuxeo.ecm.core.api.Blobs;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.TransactionalFeature;

/**
 * @since 11.1
 */
@RunWith(FeaturesRunner.class)
@Features(CoreFeature.class)
@Deploy("org.nuxeo.ecm.automation.core")
@Deploy("org.nuxeo.ecm.core.test.tests:OSGI-INF/test-coldstorage-contrib.xml")
public class RetrieveFromColdStorageTest {

    @Inject
    protected CoreSession session;

    @Inject
    protected AutomationService automationService;

    @Inject
    protected TransactionalFeature txFeature;

    @Test
    public void shouldRetrieveFromColdStorage() throws OperationException, IOException {
        DocumentModel documentModel = session.createDocumentModel("/", "MyFile", "File");
        documentModel.setPropertyValue(FILE_CONTENT_PROPERTY, (Serializable) Blobs.createBlob("foo"));
        documentModel = session.createDocument(documentModel);

        // make the move to the cold storage
        try (OperationContext context = new OperationContext(session)) {
            context.setInput(documentModel);
            automationService.run(context, MoveToColdStorage.ID, Map.of());
        }

        txFeature.nextTransaction();

        // retrieve the cold storage content
        try (OperationContext context = new OperationContext(session)) {
            context.setInput(documentModel);
            DocumentModel updatedDocument = (DocumentModel) automationService.run(context, RetrieveFromColdStorage.ID,
                    Map.of("numberOfDaysOfAvailability", 7));

            assertTrue((boolean) updatedDocument.getPropertyValue(COLD_STORAGE_BEIGN_RETRIEVED_PROPERTY));
        }
    }
}
