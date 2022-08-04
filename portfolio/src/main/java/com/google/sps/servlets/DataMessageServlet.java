// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.gson.Gson;

import data.Message;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** Servlet responsible for creating new tasks. */
@WebServlet("/stored-data")
public class DataMessageServlet extends HttpServlet {
   
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Message submitted
        String message = request.getParameter("text-input");

        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Message");
        FullEntity entity =
            Entity.newBuilder(keyFactory.newKey())
            .set("message", message)
            .build();
        datastore.put(entity);

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        Query < Entity > query =
            Query.newEntityQueryBuilder().setKind("Message").build();
        QueryResults < Entity > results = datastore.run(query);

        List < Message > messageList = new ArrayList < > ();
        while (results.hasNext()) {
            Entity entity = results.next();

            long id = entity.getKey().getId();
            String message = entity.getString("message");

            Message singleMessage = new Message(id, message);
            messageList.add(singleMessage);
        }

        Gson gson = new Gson();

        response.setContentType("application/json;");
        response.getWriter().println(gson.toJson(messageList));
    }
}
