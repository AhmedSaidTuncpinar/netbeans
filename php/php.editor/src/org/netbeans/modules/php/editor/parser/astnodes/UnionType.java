/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.php.editor.parser.astnodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.netbeans.modules.php.editor.model.impl.Type;

/**
 * Represents the union type.
 *
 * PHP 8.0: Union Types 2.0
 * https://wiki.php.net/rfc/union_types_v2
 *
 * e.g.
 * <pre>
 * private int|float $number;,
 * public function setNumber(int|float $number): void {},
 * public function getNumber(): int|float {}
 * public function dnf(): (X&Y)|Z {} // PHP 8.2 dnf types
 * </pre>
 */
public class UnionType extends Expression {

    private final List<Expression> types = new ArrayList<>();

    public UnionType(int start, int end, List<Expression> types) {
        super(start, end);
        this.types.addAll(types);
    }

    public List<Expression> getTypes() {
        return Collections.unmodifiableList(types);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        getTypes().forEach(type -> {
            if (sb.length() > 0) {
                sb.append(Type.SEPARATOR);
            }
            if (type instanceof IntersectionType) {
                sb.append("(").append(type).append(")"); // NOI18N dnf type
            } else {
                sb.append(type);
            }
        });
        return sb.toString();
    }

}
