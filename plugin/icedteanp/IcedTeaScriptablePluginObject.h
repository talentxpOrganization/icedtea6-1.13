/* IcedTeaScriptablePluginObject.h

   Copyright (C) 2009  Red Hat

This file is part of IcedTea.

IcedTea is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2, or (at your option)
any later version.

IcedTea is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
General Public License for more details.

You should have received a copy of the GNU General Public License
along with IcedTea; see the file COPYING.  If not, write to the
Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
02110-1301 USA.

Linking this library statically or dynamically with other modules is
making a combined work based on this library.  Thus, the terms and
conditions of the GNU General Public License cover the whole
combination.

As a special exception, the copyright holders of this library give you
permission to link this library with independent modules to produce an
executable, regardless of the license terms of these independent
modules, and to copy and distribute the resulting executable under
terms of your choice, provided that you also meet, for each linked
independent module, the terms and conditions of the license of that
module.  An independent module is a module which is not derived from
or based on this library.  If you modify this library, you may extend
this exception to your version of the library, but you are not
obligated to do so.  If you do not wish to do so, delete this
exception statement from your version. */

#ifndef __ICEDTEASCRIPTABLEPLUGINOBJECT_H_
#define __ICEDTEASCRIPTABLEPLUGINOBJECT_H_

#include "npupp.h"

#include "IcedTeaJavaRequestProcessor.h"
#include "IcedTeaNPPlugin.h"

/**
 * IcedTeaScriptablePluginObject, an extended NPObject that implements
 * static functions whose pointers are supplied to NPClass.
 */

class IcedTeaScriptablePluginObject: public NPObject
{

    private:
    	NPP instance;

    public:
        IcedTeaScriptablePluginObject(NPP instance);

        static void deAllocate(NPObject *npobj);

        static void invalidate(NPObject *npobj);

        static bool hasMethod(NPObject *npobj, NPIdentifier name);

        static bool invoke(NPObject *npobj, NPIdentifier name,
                const NPVariant *args, uint32_t argCount, NPVariant *result);

        static bool invokeDefault(NPObject *npobj, const NPVariant *args,
                uint32_t argCount, NPVariant *result);

        static bool hasProperty(NPObject *npobj, NPIdentifier name);

        static bool getProperty(NPObject *npobj, NPIdentifier name,
                NPVariant *result);

        static bool setProperty(NPObject *npobj, NPIdentifier name,
                const NPVariant *value);

        static bool removeProperty(NPObject *npobj, NPIdentifier name);

        static bool enumerate(NPObject *npobj, NPIdentifier **value,
                uint32_t *count);

        static bool construct(NPObject *npobj, const NPVariant *args,
                uint32_t argCount, NPVariant *result);

        static NPObject* get_scriptable_java_package_object(NPP instance, const NPUTF8* name);
};

NPObject* voidallocate_scriptable_jp_object(NPP npp, NPClass *aClass);

class IcedTeaScriptableJavaPackageObject: public NPObject
{

    private:
    	NPP instance;
    	std::string* package_name;

    public:
    	IcedTeaScriptableJavaPackageObject(NPP instance);

    	~IcedTeaScriptableJavaPackageObject();

    	void setPackageName(const NPUTF8* name);

    	std::string getPackageName();

        static void deAllocate(NPObject *npobj);

        static void invalidate(NPObject *npobj);

        static bool hasMethod(NPObject *npobj, NPIdentifier name);

        static bool invoke(NPObject *npobj, NPIdentifier name,
                const NPVariant *args, uint32_t argCount, NPVariant *result);

        static bool invokeDefault(NPObject *npobj, const NPVariant *args,
                uint32_t argCount, NPVariant *result);

        static bool hasProperty(NPObject *npobj, NPIdentifier name);

        static bool getProperty(NPObject *npobj, NPIdentifier name,
                NPVariant *result);

        static bool setProperty(NPObject *npobj, NPIdentifier name,
                const NPVariant *value);

        static bool removeProperty(NPObject *npobj, NPIdentifier name);

        static bool enumerate(NPObject *npobj, NPIdentifier **value,
                uint32_t *count);

        static bool construct(NPObject *npobj, const NPVariant *args,
                uint32_t argCount, NPVariant *result);

        static NPObject* get_scriptable_java_object(NPP instance, std::string class_id, std::string instance_id);
};

class IcedTeaScriptableJavaObject: public NPObject
{

    private:
    	NPP instance;
    	std::string* class_id;
    	std::string* instance_id;

    public:
    	IcedTeaScriptableJavaObject(NPP instance);

    	~IcedTeaScriptableJavaObject();

    	void setClassIdentifier(std::string class_id);

    	void setInstanceIdentifier(std::string instance_id);

    	std::string getClassID() { return *class_id; }

    	std::string getInstanceID() { return *instance_id; }

        static void deAllocate(NPObject *npobj);

        static void invalidate(NPObject *npobj);

        static bool hasMethod(NPObject *npobj, NPIdentifier name);

        static bool invoke(NPObject *npobj, NPIdentifier name,
                const NPVariant *args, uint32_t argCount, NPVariant *result);

        static bool invokeDefault(NPObject *npobj, const NPVariant *args,
                uint32_t argCount, NPVariant *result);

        static bool hasProperty(NPObject *npobj, NPIdentifier name);

        static bool getProperty(NPObject *npobj, NPIdentifier name,
                NPVariant *result);

        static bool setProperty(NPObject *npobj, NPIdentifier name,
                const NPVariant *value);

        static bool removeProperty(NPObject *npobj, NPIdentifier name);

        static bool enumerate(NPObject *npobj, NPIdentifier **value,
                uint32_t *count);

        static bool construct(NPObject *npobj, const NPVariant *args,
                uint32_t argCount, NPVariant *result);
};

#endif /* __ICEDTEASCRIPTABLEPLUGINOBJECT_H_ */