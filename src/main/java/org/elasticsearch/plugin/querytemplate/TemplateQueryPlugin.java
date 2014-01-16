/**
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.plugin.querytemplate;

import java.util.Collection;

import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.index.query.template.TemplateEngine;
import org.elasticsearch.plugins.AbstractPlugin;
import org.elasticsearch.script.ScriptModule;

/**
 * Plugin to enable referencing query templates and parameters.
 * 
 * This class is also referenced in the plugin configuration - make sure
 * you change the class name in src/main/resources/es.plugin.properties
 * when refactoring this class to a different name.
 * 
 */
public class TemplateQueryPlugin extends AbstractPlugin {

    @Override
    public String name() {
        return "query-template";
    }

    @Override
    public String description() {
        return "Query template plugin allowing to add reference queries by template name";
    }

    /**
     * Make sure the new query type is registered on index loading.
     * */
    @Override
    public Collection<Class<? extends Module>> indexModules() {
        Collection<Class<? extends Module>> modules = Lists.newArrayList();
        modules.add(TemplateQueryParserModule.class);
        return modules;
    }
    
    public void onModule(ScriptModule module) {
        module.addScriptEngine(TemplateEngine.class);
    }
}
