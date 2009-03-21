package rpgeeze.gl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

/**
 * OpenGL interface object that delegates to another interface object.
 * 
 */
public abstract class DelegatingGL implements javax.media.opengl.GL {
	private final javax.media.opengl.GL delegate;
	
	public DelegatingGL(javax.media.opengl.GL delegate) {
		this.delegate = delegate;
	}

	public Object getExtension(String extensionName) {
		return delegate.getExtension(extensionName);
	}

	public Object getPlatformGLExtensions() {
		return delegate.getPlatformGLExtensions();
	}

	public void glAccum(int op, float value) {
		delegate.glAccum(op, value);
	}

	public void glActiveStencilFaceEXT(int mode) {
		delegate.glActiveStencilFaceEXT(mode);
	}

	public void glActiveTexture(int mode) {
		delegate.glActiveTexture(mode);
	}

	public void glActiveVaryingNV(int program, byte[] name, int name_offset) {
		delegate.glActiveVaryingNV(program, name, name_offset);
	}

	public void glActiveVaryingNV(int program, ByteBuffer name) {
		delegate.glActiveVaryingNV(program, name);
	}

	public ByteBuffer glAllocateMemoryNV(int arg0, float arg1, float arg2,
			float arg3) {
		return delegate.glAllocateMemoryNV(arg0, arg1, arg2, arg3);
	}

	public void glAlphaFragmentOp1ATI(int stage, int portion, int variable,
			int input, int mapping, int componentUsage) {
		delegate.glAlphaFragmentOp1ATI(stage, portion, variable, input,
				mapping, componentUsage);
	}

	public void glAlphaFragmentOp2ATI(int op, int dst, int dstMod, int arg1,
			int arg1Rep, int arg1Mod, int arg2, int arg2Rep, int arg2Mod) {
		delegate.glAlphaFragmentOp2ATI(op, dst, dstMod, arg1, arg1Rep, arg1Mod,
				arg2, arg2Rep, arg2Mod);
	}

	public void glAlphaFragmentOp3ATI(int op, int dst, int dstMod, int arg1,
			int arg1Rep, int arg1Mod, int arg2, int arg2Rep, int arg2Mod,
			int arg3, int arg3Rep, int arg3Mod) {
		delegate.glAlphaFragmentOp3ATI(op, dst, dstMod, arg1, arg1Rep, arg1Mod,
				arg2, arg2Rep, arg2Mod, arg3, arg3Rep, arg3Mod);
	}

	public void glAlphaFunc(int func, float ref) {
		delegate.glAlphaFunc(func, ref);
	}

	public void glApplyTextureEXT(int mode) {
		delegate.glApplyTextureEXT(mode);
	}

	public boolean glAreProgramsResidentNV(int n, int[] textures,
			int textures_offset, byte[] residences, int residences_offset) {
		return delegate.glAreProgramsResidentNV(n, textures, textures_offset,
				residences, residences_offset);
	}

	public boolean glAreProgramsResidentNV(int n, IntBuffer textures,
			ByteBuffer residences) {
		return delegate.glAreProgramsResidentNV(n, textures, residences);
	}

	public boolean glAreTexturesResident(int n, int[] textures,
			int textures_offset, byte[] residences, int residences_offset) {
		return delegate.glAreTexturesResident(n, textures, textures_offset,
				residences, residences_offset);
	}

	public boolean glAreTexturesResident(int n, IntBuffer textures,
			ByteBuffer residences) {
		return delegate.glAreTexturesResident(n, textures, residences);
	}

	public void glArrayElement(int i) {
		delegate.glArrayElement(i);
	}

	public void glArrayObjectATI(int array, int size, int type, int stride,
			int buffer, int offset) {
		delegate.glArrayObjectATI(array, size, type, stride, buffer, offset);
	}

	public void glAsyncMarkerSGIX(int mode) {
		delegate.glAsyncMarkerSGIX(mode);
	}

	public void glAttachObjectARB(int target, int id) {
		delegate.glAttachObjectARB(target, id);
	}

	public void glAttachShader(int target, int id) {
		delegate.glAttachShader(target, id);
	}

	public void glBegin(int mode) {
		delegate.glBegin(mode);
	}

	public void glBeginFragmentShaderATI() {
		delegate.glBeginFragmentShaderATI();
	}

	public void glBeginOcclusionQueryNV(int mode) {
		delegate.glBeginOcclusionQueryNV(mode);
	}

	public void glBeginQuery(int target, int id) {
		delegate.glBeginQuery(target, id);
	}

	public void glBeginQueryARB(int target, int id) {
		delegate.glBeginQueryARB(target, id);
	}

	public void glBeginTransformFeedbackNV(int primitiveMode) {
		delegate.glBeginTransformFeedbackNV(primitiveMode);
	}

	public void glBeginVertexShaderEXT() {
		delegate.glBeginVertexShaderEXT();
	}

	public void glBindAttribLocation(int program, int index, String name) {
		delegate.glBindAttribLocation(program, index, name);
	}

	public void glBindAttribLocationARB(int program, int index, String name) {
		delegate.glBindAttribLocationARB(program, index, name);
	}

	public void glBindBuffer(int target, int id) {
		delegate.glBindBuffer(target, id);
	}

	public void glBindBufferARB(int target, int id) {
		delegate.glBindBufferARB(target, id);
	}

	public void glBindBufferBaseNV(int target, int index, int buffer) {
		delegate.glBindBufferBaseNV(target, index, buffer);
	}

	public void glBindBufferOffsetNV(int target, int index, int buffer,
			int offset) {
		delegate.glBindBufferOffsetNV(target, index, buffer, offset);
	}

	public void glBindBufferRangeNV(int target, int index, int buffer,
			int offset, int size) {
		delegate.glBindBufferRangeNV(target, index, buffer, offset, size);
	}

	public void glBindFragDataLocationEXT(int program, int color, byte[] name,
			int name_offset) {
		delegate.glBindFragDataLocationEXT(program, color, name, name_offset);
	}

	public void glBindFragDataLocationEXT(int program, int color,
			ByteBuffer name) {
		delegate.glBindFragDataLocationEXT(program, color, name);
	}

	public void glBindFragmentShaderATI(int mode) {
		delegate.glBindFragmentShaderATI(mode);
	}

	public void glBindFramebufferEXT(int target, int id) {
		delegate.glBindFramebufferEXT(target, id);
	}

	public int glBindLightParameterEXT(int light, int value) {
		return delegate.glBindLightParameterEXT(light, value);
	}

	public int glBindMaterialParameterEXT(int light, int value) {
		return delegate.glBindMaterialParameterEXT(light, value);
	}

	public int glBindParameterEXT(int type) {
		return delegate.glBindParameterEXT(type);
	}

	public void glBindProgramARB(int target, int id) {
		delegate.glBindProgramARB(target, id);
	}

	public void glBindProgramNV(int target, int id) {
		delegate.glBindProgramNV(target, id);
	}

	public void glBindRenderbufferEXT(int target, int id) {
		delegate.glBindRenderbufferEXT(target, id);
	}

	public int glBindTexGenParameterEXT(int unit, int coord, int value) {
		return delegate.glBindTexGenParameterEXT(unit, coord, value);
	}

	public void glBindTexture(int target, int texture) {
		delegate.glBindTexture(target, texture);
	}

	public int glBindTextureUnitParameterEXT(int light, int value) {
		return delegate.glBindTextureUnitParameterEXT(light, value);
	}

	public void glBindVertexArrayAPPLE(int mode) {
		delegate.glBindVertexArrayAPPLE(mode);
	}

	public void glBindVertexShaderEXT(int mode) {
		delegate.glBindVertexShaderEXT(mode);
	}

	public void glBitmap(int width, int height, float xorig, float yorig,
			float xmove, float ymove, byte[] bitmap, int bitmap_offset) {
		delegate.glBitmap(width, height, xorig, yorig, xmove, ymove, bitmap,
				bitmap_offset);
	}

	public void glBitmap(int width, int height, float xorig, float yorig,
			float xmove, float ymove, ByteBuffer bitmap) {
		delegate.glBitmap(width, height, xorig, yorig, xmove, ymove, bitmap);
	}

	public void glBitmap(int width, int height, float xorig, float yorig,
			float xmove, float ymove, long bitmap_buffer_offset) {
		delegate.glBitmap(width, height, xorig, yorig, xmove, ymove,
				bitmap_buffer_offset);
	}

	public void glBlendColor(float red, float green, float blue, float alpha) {
		delegate.glBlendColor(red, green, blue, alpha);
	}

	public void glBlendEquation(int mode) {
		delegate.glBlendEquation(mode);
	}

	public void glBlendEquationSeparate(int target, int id) {
		delegate.glBlendEquationSeparate(target, id);
	}

	public void glBlendEquationSeparateEXT(int target, int id) {
		delegate.glBlendEquationSeparateEXT(target, id);
	}

	public void glBlendFunc(int sfactor, int dfactor) {
		delegate.glBlendFunc(sfactor, dfactor);
	}

	public void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha) {
		delegate.glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha,
				dfactorAlpha);
	}

	public void glBlendFuncSeparateEXT(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha) {
		delegate.glBlendFuncSeparateEXT(sfactorRGB, dfactorRGB, sfactorAlpha,
				dfactorAlpha);
	}

	public void glBlendFuncSeparateINGR(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha) {
		delegate.glBlendFuncSeparateINGR(sfactorRGB, dfactorRGB, sfactorAlpha,
				dfactorAlpha);
	}

	public void glBlitFramebufferEXT(int srcX0, int srcY0, int srcX1,
			int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask,
			int filter) {
		delegate.glBlitFramebufferEXT(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0,
				dstX1, dstY1, mask, filter);
	}

	public void glBufferData(int target, int size, Buffer data, int usage) {
		delegate.glBufferData(target, size, data, usage);
	}

	public void glBufferDataARB(int target, int size, Buffer data, int usage) {
		delegate.glBufferDataARB(target, size, data, usage);
	}

	public void glBufferParameteriAPPLE(int target, int pname, int params) {
		delegate.glBufferParameteriAPPLE(target, pname, params);
	}

	public int glBufferRegionEnabled() {
		return delegate.glBufferRegionEnabled();
	}

	public void glBufferSubData(int target, int offset, int size, Buffer data) {
		delegate.glBufferSubData(target, offset, size, data);
	}

	public void glBufferSubDataARB(int target, int offset, int size, Buffer data) {
		delegate.glBufferSubDataARB(target, offset, size, data);
	}

	public void glCallList(int list) {
		delegate.glCallList(list);
	}

	public void glCallLists(int n, int type, Buffer lists) {
		delegate.glCallLists(n, type, lists);
	}

	public int glCheckFramebufferStatusEXT(int type) {
		return delegate.glCheckFramebufferStatusEXT(type);
	}

	public void glClampColorARB(int target, int id) {
		delegate.glClampColorARB(target, id);
	}

	public void glClear(int mask) {
		delegate.glClear(mask);
	}

	public void glClearAccum(float red, float green, float blue, float alpha) {
		delegate.glClearAccum(red, green, blue, alpha);
	}

	public void glClearColor(float red, float green, float blue, float alpha) {
		delegate.glClearColor(red, green, blue, alpha);
	}

	public void glClearColorIiEXT(int red, int green, int blue, int alpha) {
		delegate.glClearColorIiEXT(red, green, blue, alpha);
	}

	public void glClearColorIuiEXT(int red, int green, int blue, int alpha) {
		delegate.glClearColorIuiEXT(red, green, blue, alpha);
	}

	public void glClearDepth(double depth) {
		delegate.glClearDepth(depth);
	}

	public void glClearDepthdNV(double depth) {
		delegate.glClearDepthdNV(depth);
	}

	public void glClearIndex(float c) {
		delegate.glClearIndex(c);
	}

	public void glClearStencil(int s) {
		delegate.glClearStencil(s);
	}

	public void glClientActiveTexture(int mode) {
		delegate.glClientActiveTexture(mode);
	}

	public void glClientActiveVertexStreamATI(int mode) {
		delegate.glClientActiveVertexStreamATI(mode);
	}

	public void glClipPlane(int plane, double[] equation, int equation_offset) {
		delegate.glClipPlane(plane, equation, equation_offset);
	}

	public void glClipPlane(int plane, DoubleBuffer equation) {
		delegate.glClipPlane(plane, equation);
	}

	public void glColor3b(byte red, byte green, byte blue) {
		delegate.glColor3b(red, green, blue);
	}

	public void glColor3bv(byte[] v, int v_offset) {
		delegate.glColor3bv(v, v_offset);
	}

	public void glColor3bv(ByteBuffer v) {
		delegate.glColor3bv(v);
	}

	public void glColor3d(double red, double green, double blue) {
		delegate.glColor3d(red, green, blue);
	}

	public void glColor3dv(double[] v, int v_offset) {
		delegate.glColor3dv(v, v_offset);
	}

	public void glColor3dv(DoubleBuffer v) {
		delegate.glColor3dv(v);
	}

	public void glColor3f(float red, float green, float blue) {
		delegate.glColor3f(red, green, blue);
	}

	public void glColor3fv(float[] v, int v_offset) {
		delegate.glColor3fv(v, v_offset);
	}

	public void glColor3fv(FloatBuffer v) {
		delegate.glColor3fv(v);
	}

	public void glColor3fVertex3fSUN(float r, float g, float b, float x,
			float y, float z) {
		delegate.glColor3fVertex3fSUN(r, g, b, x, y, z);
	}

	public void glColor3fVertex3fvSUN(float[] c, int c_offset, float[] v,
			int v_offset) {
		delegate.glColor3fVertex3fvSUN(c, c_offset, v, v_offset);
	}

	public void glColor3fVertex3fvSUN(FloatBuffer c, FloatBuffer v) {
		delegate.glColor3fVertex3fvSUN(c, v);
	}

	public void glColor3hNV(short red, short green, short blue) {
		delegate.glColor3hNV(red, green, blue);
	}

	public void glColor3hvNV(short[] v, int v_offset) {
		delegate.glColor3hvNV(v, v_offset);
	}

	public void glColor3hvNV(ShortBuffer v) {
		delegate.glColor3hvNV(v);
	}

	public void glColor3i(int red, int green, int blue) {
		delegate.glColor3i(red, green, blue);
	}

	public void glColor3iv(int[] v, int v_offset) {
		delegate.glColor3iv(v, v_offset);
	}

	public void glColor3iv(IntBuffer v) {
		delegate.glColor3iv(v);
	}

	public void glColor3s(short red, short green, short blue) {
		delegate.glColor3s(red, green, blue);
	}

	public void glColor3sv(short[] v, int v_offset) {
		delegate.glColor3sv(v, v_offset);
	}

	public void glColor3sv(ShortBuffer v) {
		delegate.glColor3sv(v);
	}

	public void glColor3ub(byte red, byte green, byte blue) {
		delegate.glColor3ub(red, green, blue);
	}

	public void glColor3ubv(byte[] v, int v_offset) {
		delegate.glColor3ubv(v, v_offset);
	}

	public void glColor3ubv(ByteBuffer v) {
		delegate.glColor3ubv(v);
	}

	public void glColor3ui(int red, int green, int blue) {
		delegate.glColor3ui(red, green, blue);
	}

	public void glColor3uiv(int[] v, int v_offset) {
		delegate.glColor3uiv(v, v_offset);
	}

	public void glColor3uiv(IntBuffer v) {
		delegate.glColor3uiv(v);
	}

	public void glColor3us(short red, short green, short blue) {
		delegate.glColor3us(red, green, blue);
	}

	public void glColor3usv(short[] v, int v_offset) {
		delegate.glColor3usv(v, v_offset);
	}

	public void glColor3usv(ShortBuffer v) {
		delegate.glColor3usv(v);
	}

	public void glColor4b(byte red, byte green, byte blue, byte alpha) {
		delegate.glColor4b(red, green, blue, alpha);
	}

	public void glColor4bv(byte[] v, int v_offset) {
		delegate.glColor4bv(v, v_offset);
	}

	public void glColor4bv(ByteBuffer v) {
		delegate.glColor4bv(v);
	}

	public void glColor4d(double red, double green, double blue, double alpha) {
		delegate.glColor4d(red, green, blue, alpha);
	}

	public void glColor4dv(double[] v, int v_offset) {
		delegate.glColor4dv(v, v_offset);
	}

	public void glColor4dv(DoubleBuffer v) {
		delegate.glColor4dv(v);
	}

	public void glColor4f(float red, float green, float blue, float alpha) {
		delegate.glColor4f(red, green, blue, alpha);
	}

	public void glColor4fNormal3fVertex3fSUN(float r, float g, float b,
			float a, float nx, float ny, float nz, float x, float y, float z) {
		delegate.glColor4fNormal3fVertex3fSUN(r, g, b, a, nx, ny, nz, x, y, z);
	}

	public void glColor4fNormal3fVertex3fvSUN(float[] c, int c_offset,
			float[] n, int n_offset, float[] v, int v_offset) {
		delegate.glColor4fNormal3fVertex3fvSUN(c, c_offset, n, n_offset, v,
				v_offset);
	}

	public void glColor4fNormal3fVertex3fvSUN(FloatBuffer c, FloatBuffer n,
			FloatBuffer v) {
		delegate.glColor4fNormal3fVertex3fvSUN(c, n, v);
	}

	public void glColor4fv(float[] v, int v_offset) {
		delegate.glColor4fv(v, v_offset);
	}

	public void glColor4fv(FloatBuffer v) {
		delegate.glColor4fv(v);
	}

	public void glColor4hNV(short x, short y, short z, short w) {
		delegate.glColor4hNV(x, y, z, w);
	}

	public void glColor4hvNV(short[] v, int v_offset) {
		delegate.glColor4hvNV(v, v_offset);
	}

	public void glColor4hvNV(ShortBuffer v) {
		delegate.glColor4hvNV(v);
	}

	public void glColor4i(int red, int green, int blue, int alpha) {
		delegate.glColor4i(red, green, blue, alpha);
	}

	public void glColor4iv(int[] v, int v_offset) {
		delegate.glColor4iv(v, v_offset);
	}

	public void glColor4iv(IntBuffer v) {
		delegate.glColor4iv(v);
	}

	public void glColor4s(short red, short green, short blue, short alpha) {
		delegate.glColor4s(red, green, blue, alpha);
	}

	public void glColor4sv(short[] v, int v_offset) {
		delegate.glColor4sv(v, v_offset);
	}

	public void glColor4sv(ShortBuffer v) {
		delegate.glColor4sv(v);
	}

	public void glColor4ub(byte red, byte green, byte blue, byte alpha) {
		delegate.glColor4ub(red, green, blue, alpha);
	}

	public void glColor4ubv(byte[] v, int v_offset) {
		delegate.glColor4ubv(v, v_offset);
	}

	public void glColor4ubv(ByteBuffer v) {
		delegate.glColor4ubv(v);
	}

	public void glColor4ubVertex2fSUN(byte r, byte g, byte b, byte a, float x,
			float y) {
		delegate.glColor4ubVertex2fSUN(r, g, b, a, x, y);
	}

	public void glColor4ubVertex2fvSUN(byte[] c, int c_offset, float[] v,
			int v_offset) {
		delegate.glColor4ubVertex2fvSUN(c, c_offset, v, v_offset);
	}

	public void glColor4ubVertex2fvSUN(ByteBuffer c, FloatBuffer v) {
		delegate.glColor4ubVertex2fvSUN(c, v);
	}

	public void glColor4ubVertex3fSUN(byte r, byte g, byte b, byte a, float x,
			float y, float z) {
		delegate.glColor4ubVertex3fSUN(r, g, b, a, x, y, z);
	}

	public void glColor4ubVertex3fvSUN(byte[] c, int c_offset, float[] v,
			int v_offset) {
		delegate.glColor4ubVertex3fvSUN(c, c_offset, v, v_offset);
	}

	public void glColor4ubVertex3fvSUN(ByteBuffer c, FloatBuffer v) {
		delegate.glColor4ubVertex3fvSUN(c, v);
	}

	public void glColor4ui(int red, int green, int blue, int alpha) {
		delegate.glColor4ui(red, green, blue, alpha);
	}

	public void glColor4uiv(int[] v, int v_offset) {
		delegate.glColor4uiv(v, v_offset);
	}

	public void glColor4uiv(IntBuffer v) {
		delegate.glColor4uiv(v);
	}

	public void glColor4us(short red, short green, short blue, short alpha) {
		delegate.glColor4us(red, green, blue, alpha);
	}

	public void glColor4usv(short[] v, int v_offset) {
		delegate.glColor4usv(v, v_offset);
	}

	public void glColor4usv(ShortBuffer v) {
		delegate.glColor4usv(v);
	}

	public void glColorFragmentOp1ATI(int op, int dst, int dstMask, int dstMod,
			int arg1, int arg1Rep, int arg1Mod) {
		delegate.glColorFragmentOp1ATI(op, dst, dstMask, dstMod, arg1, arg1Rep,
				arg1Mod);
	}

	public void glColorFragmentOp2ATI(int op, int dst, int dstMask, int dstMod,
			int arg1, int arg1Rep, int arg1Mod, int arg2, int arg2Rep,
			int arg2Mod) {
		delegate.glColorFragmentOp2ATI(op, dst, dstMask, dstMod, arg1, arg1Rep,
				arg1Mod, arg2, arg2Rep, arg2Mod);
	}

	public void glColorFragmentOp3ATI(int op, int dst, int dstMask, int dstMod,
			int arg1, int arg1Rep, int arg1Mod, int arg2, int arg2Rep,
			int arg2Mod, int arg3, int arg3Rep, int arg3Mod) {
		delegate.glColorFragmentOp3ATI(op, dst, dstMask, dstMod, arg1, arg1Rep,
				arg1Mod, arg2, arg2Rep, arg2Mod, arg3, arg3Rep, arg3Mod);
	}

	public void glColorMask(boolean red, boolean green, boolean blue,
			boolean alpha) {
		delegate.glColorMask(red, green, blue, alpha);
	}

	public void glColorMaskIndexedEXT(int index, boolean r, boolean g,
			boolean b, boolean a) {
		delegate.glColorMaskIndexedEXT(index, r, g, b, a);
	}

	public void glColorMaterial(int face, int mode) {
		delegate.glColorMaterial(face, mode);
	}

	public void glColorPointer(int size, int type, int stride, Buffer ptr) {
		delegate.glColorPointer(size, type, stride, ptr);
	}

	public void glColorPointer(int size, int type, int stride,
			long ptr_buffer_offset) {
		delegate.glColorPointer(size, type, stride, ptr_buffer_offset);
	}

	public void glColorSubTable(int target, int start, int count, int format,
			int type, Buffer data) {
		delegate.glColorSubTable(target, start, count, format, type, data);
	}

	public void glColorSubTable(int target, int start, int count, int format,
			int type, long data_buffer_offset) {
		delegate.glColorSubTable(target, start, count, format, type,
				data_buffer_offset);
	}

	public void glColorTable(int target, int internalformat, int width,
			int format, int type, Buffer table) {
		delegate.glColorTable(target, internalformat, width, format, type,
				table);
	}

	public void glColorTable(int target, int internalformat, int width,
			int format, int type, long table_buffer_offset) {
		delegate.glColorTable(target, internalformat, width, format, type,
				table_buffer_offset);
	}

	public void glColorTableEXT(int target, int internalformat, int width,
			int format, int type, Buffer table) {
		delegate.glColorTableEXT(target, internalformat, width, format, type,
				table);
	}

	public void glColorTableParameterfv(int target, int pname, float[] params,
			int params_offset) {
		delegate.glColorTableParameterfv(target, pname, params, params_offset);
	}

	public void glColorTableParameterfv(int target, int pname,
			FloatBuffer params) {
		delegate.glColorTableParameterfv(target, pname, params);
	}

	public void glColorTableParameteriv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glColorTableParameteriv(target, pname, params, params_offset);
	}

	public void glColorTableParameteriv(int target, int pname, IntBuffer params) {
		delegate.glColorTableParameteriv(target, pname, params);
	}

	public void glCombinerInputNV(int stage, int portion, int variable,
			int input, int mapping, int componentUsage) {
		delegate.glCombinerInputNV(stage, portion, variable, input, mapping,
				componentUsage);
	}

	public void glCombinerOutputNV(int stage, int portion, int abOutput,
			int cdOutput, int sumOutput, int scale, int bias,
			boolean abDotProduct, boolean cdDotProduct, boolean muxSum) {
		delegate.glCombinerOutputNV(stage, portion, abOutput, cdOutput,
				sumOutput, scale, bias, abDotProduct, cdDotProduct, muxSum);
	}

	public void glCombinerParameterfNV(int target, float s) {
		delegate.glCombinerParameterfNV(target, s);
	}

	public void glCombinerParameterfvNV(int target, float[] v, int v_offset) {
		delegate.glCombinerParameterfvNV(target, v, v_offset);
	}

	public void glCombinerParameterfvNV(int target, FloatBuffer v) {
		delegate.glCombinerParameterfvNV(target, v);
	}

	public void glCombinerParameteriNV(int target, int s) {
		delegate.glCombinerParameteriNV(target, s);
	}

	public void glCombinerParameterivNV(int target, int[] v, int v_offset) {
		delegate.glCombinerParameterivNV(target, v, v_offset);
	}

	public void glCombinerParameterivNV(int target, IntBuffer v) {
		delegate.glCombinerParameterivNV(target, v);
	}

	public void glCombinerStageParameterfvNV(int target, int pname,
			float[] params, int params_offset) {
		delegate.glCombinerStageParameterfvNV(target, pname, params,
				params_offset);
	}

	public void glCombinerStageParameterfvNV(int target, int pname,
			FloatBuffer params) {
		delegate.glCombinerStageParameterfvNV(target, pname, params);
	}

	public void glCompileShader(int mode) {
		delegate.glCompileShader(mode);
	}

	public void glCompileShaderARB(int mode) {
		delegate.glCompileShaderARB(mode);
	}

	public void glCompressedTexImage1D(int target, int level,
			int internalformat, int width, int border, int imageSize,
			Buffer data) {
		delegate.glCompressedTexImage1D(target, level, internalformat, width,
				border, imageSize, data);
	}

	public void glCompressedTexImage1D(int target, int level,
			int internalformat, int width, int border, int imageSize,
			long data_buffer_offset) {
		delegate.glCompressedTexImage1D(target, level, internalformat, width,
				border, imageSize, data_buffer_offset);
	}

	public void glCompressedTexImage2D(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, Buffer data) {
		delegate.glCompressedTexImage2D(target, level, internalformat, width,
				height, border, imageSize, data);
	}

	public void glCompressedTexImage2D(int target, int level,
			int internalformat, int width, int height, int border,
			int imageSize, long data_buffer_offset) {
		delegate.glCompressedTexImage2D(target, level, internalformat, width,
				height, border, imageSize, data_buffer_offset);
	}

	public void glCompressedTexImage3D(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, Buffer data) {
		delegate.glCompressedTexImage3D(target, level, internalformat, width,
				height, depth, border, imageSize, data);
	}

	public void glCompressedTexImage3D(int target, int level,
			int internalformat, int width, int height, int depth, int border,
			int imageSize, long data_buffer_offset) {
		delegate.glCompressedTexImage3D(target, level, internalformat, width,
				height, depth, border, imageSize, data_buffer_offset);
	}

	public void glCompressedTexSubImage1D(int target, int level, int xoffset,
			int width, int format, int imageSize, Buffer data) {
		delegate.glCompressedTexSubImage1D(target, level, xoffset, width,
				format, imageSize, data);
	}

	public void glCompressedTexSubImage1D(int target, int level, int xoffset,
			int width, int format, int imageSize, long data_buffer_offset) {
		delegate.glCompressedTexSubImage1D(target, level, xoffset, width,
				format, imageSize, data_buffer_offset);
	}

	public void glCompressedTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int imageSize,
			Buffer data) {
		delegate.glCompressedTexSubImage2D(target, level, xoffset, yoffset,
				width, height, format, imageSize, data);
	}

	public void glCompressedTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int imageSize,
			long data_buffer_offset) {
		delegate.glCompressedTexSubImage2D(target, level, xoffset, yoffset,
				width, height, format, imageSize, data_buffer_offset);
	}

	public void glCompressedTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int imageSize, Buffer data) {
		delegate.glCompressedTexSubImage3D(target, level, xoffset, yoffset,
				zoffset, width, height, depth, format, imageSize, data);
	}

	public void glCompressedTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int imageSize, long data_buffer_offset) {
		delegate.glCompressedTexSubImage3D(target, level, xoffset, yoffset,
				zoffset, width, height, depth, format, imageSize,
				data_buffer_offset);
	}

	public void glConvolutionFilter1D(int target, int internalformat,
			int width, int format, int type, Buffer table) {
		delegate.glConvolutionFilter1D(target, internalformat, width, format,
				type, table);
	}

	public void glConvolutionFilter1D(int target, int internalformat,
			int width, int format, int type, long table_buffer_offset) {
		delegate.glConvolutionFilter1D(target, internalformat, width, format,
				type, table_buffer_offset);
	}

	public void glConvolutionFilter2D(int target, int internalformat,
			int width, int height, int format, int type, Buffer image) {
		delegate.glConvolutionFilter2D(target, internalformat, width, height,
				format, type, image);
	}

	public void glConvolutionFilter2D(int target, int internalformat,
			int width, int height, int format, int type,
			long image_buffer_offset) {
		delegate.glConvolutionFilter2D(target, internalformat, width, height,
				format, type, image_buffer_offset);
	}

	public void glConvolutionParameterf(int target, int pname, float params) {
		delegate.glConvolutionParameterf(target, pname, params);
	}

	public void glConvolutionParameterfv(int target, int pname, float[] params,
			int params_offset) {
		delegate.glConvolutionParameterfv(target, pname, params, params_offset);
	}

	public void glConvolutionParameterfv(int target, int pname,
			FloatBuffer params) {
		delegate.glConvolutionParameterfv(target, pname, params);
	}

	public void glConvolutionParameteri(int target, int pname, int params) {
		delegate.glConvolutionParameteri(target, pname, params);
	}

	public void glConvolutionParameteriv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glConvolutionParameteriv(target, pname, params, params_offset);
	}

	public void glConvolutionParameteriv(int target, int pname, IntBuffer params) {
		delegate.glConvolutionParameteriv(target, pname, params);
	}

	public void glCopyColorSubTable(int target, int start, int x, int y,
			int width) {
		delegate.glCopyColorSubTable(target, start, x, y, width);
	}

	public void glCopyColorTable(int target, int internalformat, int x, int y,
			int width) {
		delegate.glCopyColorTable(target, internalformat, x, y, width);
	}

	public void glCopyConvolutionFilter1D(int target, int internalformat,
			int x, int y, int width) {
		delegate.glCopyConvolutionFilter1D(target, internalformat, x, y, width);
	}

	public void glCopyConvolutionFilter2D(int target, int internalformat,
			int x, int y, int width, int height) {
		delegate.glCopyConvolutionFilter2D(target, internalformat, x, y, width,
				height);
	}

	public void glCopyPixels(int x, int y, int width, int height, int type) {
		delegate.glCopyPixels(x, y, width, height, type);
	}

	public void glCopyTexImage1D(int target, int level, int internalformat,
			int x, int y, int width, int border) {
		delegate.glCopyTexImage1D(target, level, internalformat, x, y, width,
				border);
	}

	public void glCopyTexImage2D(int target, int level, int internalformat,
			int x, int y, int width, int height, int border) {
		delegate.glCopyTexImage2D(target, level, internalformat, x, y, width,
				height, border);
	}

	public void glCopyTexSubImage1D(int target, int level, int xoffset, int x,
			int y, int width) {
		delegate.glCopyTexSubImage1D(target, level, xoffset, x, y, width);
	}

	public void glCopyTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int x, int y, int width, int height) {
		delegate.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y,
				width, height);
	}

	public void glCopyTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int x, int y, int width, int height) {
		delegate.glCopyTexSubImage3D(target, level, xoffset, yoffset, zoffset,
				x, y, width, height);
	}

	public int glCreateProgram() {
		return delegate.glCreateProgram();
	}

	public int glCreateProgramObjectARB() {
		return delegate.glCreateProgramObjectARB();
	}

	public int glCreateShader(int type) {
		return delegate.glCreateShader(type);
	}

	public int glCreateShaderObjectARB(int type) {
		return delegate.glCreateShaderObjectARB(type);
	}

	public void glCullFace(int mode) {
		delegate.glCullFace(mode);
	}

	public void glCullParameterdvEXT(int pname, double[] params,
			int params_offset) {
		delegate.glCullParameterdvEXT(pname, params, params_offset);
	}

	public void glCullParameterdvEXT(int pname, DoubleBuffer params) {
		delegate.glCullParameterdvEXT(pname, params);
	}

	public void glCullParameterfvEXT(int pname, float[] params,
			int params_offset) {
		delegate.glCullParameterfvEXT(pname, params, params_offset);
	}

	public void glCullParameterfvEXT(int pname, FloatBuffer params) {
		delegate.glCullParameterfvEXT(pname, params);
	}

	public void glCurrentPaletteMatrixARB(int count) {
		delegate.glCurrentPaletteMatrixARB(count);
	}

	public void glDeformationMap3dSGIX(int target, double u1, double u2,
			int ustride, int uorder, double v1, double v2, int vstride,
			int vorder, double w1, double w2, int wstride, int worder,
			double[] points, int points_offset) {
		delegate.glDeformationMap3dSGIX(target, u1, u2, ustride, uorder, v1,
				v2, vstride, vorder, w1, w2, wstride, worder, points,
				points_offset);
	}

	public void glDeformationMap3dSGIX(int target, double u1, double u2,
			int ustride, int uorder, double v1, double v2, int vstride,
			int vorder, double w1, double w2, int wstride, int worder,
			DoubleBuffer points) {
		delegate.glDeformationMap3dSGIX(target, u1, u2, ustride, uorder, v1,
				v2, vstride, vorder, w1, w2, wstride, worder, points);
	}

	public void glDeformationMap3fSGIX(int target, float u1, float u2,
			int ustride, int uorder, float v1, float v2, int vstride,
			int vorder, float w1, float w2, int wstride, int worder,
			float[] points, int points_offset) {
		delegate.glDeformationMap3fSGIX(target, u1, u2, ustride, uorder, v1,
				v2, vstride, vorder, w1, w2, wstride, worder, points,
				points_offset);
	}

	public void glDeformationMap3fSGIX(int target, float u1, float u2,
			int ustride, int uorder, float v1, float v2, int vstride,
			int vorder, float w1, float w2, int wstride, int worder,
			FloatBuffer points) {
		delegate.glDeformationMap3fSGIX(target, u1, u2, ustride, uorder, v1,
				v2, vstride, vorder, w1, w2, wstride, worder, points);
	}

	public void glDeformSGIX(int mode) {
		delegate.glDeformSGIX(mode);
	}

	public void glDeleteAsyncMarkersSGIX(int target, int s) {
		delegate.glDeleteAsyncMarkersSGIX(target, s);
	}

	public void glDeleteBufferRegion(int mode) {
		delegate.glDeleteBufferRegion(mode);
	}

	public void glDeleteBuffers(int n, int[] ids, int ids_offset) {
		delegate.glDeleteBuffers(n, ids, ids_offset);
	}

	public void glDeleteBuffers(int n, IntBuffer ids) {
		delegate.glDeleteBuffers(n, ids);
	}

	public void glDeleteBuffersARB(int n, int[] ids, int ids_offset) {
		delegate.glDeleteBuffersARB(n, ids, ids_offset);
	}

	public void glDeleteBuffersARB(int n, IntBuffer ids) {
		delegate.glDeleteBuffersARB(n, ids);
	}

	public void glDeleteFencesAPPLE(int n, int[] ids, int ids_offset) {
		delegate.glDeleteFencesAPPLE(n, ids, ids_offset);
	}

	public void glDeleteFencesAPPLE(int n, IntBuffer ids) {
		delegate.glDeleteFencesAPPLE(n, ids);
	}

	public void glDeleteFencesNV(int n, int[] ids, int ids_offset) {
		delegate.glDeleteFencesNV(n, ids, ids_offset);
	}

	public void glDeleteFencesNV(int n, IntBuffer ids) {
		delegate.glDeleteFencesNV(n, ids);
	}

	public void glDeleteFragmentShaderATI(int mode) {
		delegate.glDeleteFragmentShaderATI(mode);
	}

	public void glDeleteFramebuffersEXT(int n, int[] ids, int ids_offset) {
		delegate.glDeleteFramebuffersEXT(n, ids, ids_offset);
	}

	public void glDeleteFramebuffersEXT(int n, IntBuffer ids) {
		delegate.glDeleteFramebuffersEXT(n, ids);
	}

	public void glDeleteLists(int list, int range) {
		delegate.glDeleteLists(list, range);
	}

	public void glDeleteObjectARB(int mode) {
		delegate.glDeleteObjectARB(mode);
	}

	public void glDeleteOcclusionQueriesNV(int n, int[] ids, int ids_offset) {
		delegate.glDeleteOcclusionQueriesNV(n, ids, ids_offset);
	}

	public void glDeleteOcclusionQueriesNV(int n, IntBuffer ids) {
		delegate.glDeleteOcclusionQueriesNV(n, ids);
	}

	public void glDeleteProgram(int mode) {
		delegate.glDeleteProgram(mode);
	}

	public void glDeleteProgramsARB(int n, int[] ids, int ids_offset) {
		delegate.glDeleteProgramsARB(n, ids, ids_offset);
	}

	public void glDeleteProgramsARB(int n, IntBuffer ids) {
		delegate.glDeleteProgramsARB(n, ids);
	}

	public void glDeleteProgramsNV(int n, int[] ids, int ids_offset) {
		delegate.glDeleteProgramsNV(n, ids, ids_offset);
	}

	public void glDeleteProgramsNV(int n, IntBuffer ids) {
		delegate.glDeleteProgramsNV(n, ids);
	}

	public void glDeleteQueries(int n, int[] ids, int ids_offset) {
		delegate.glDeleteQueries(n, ids, ids_offset);
	}

	public void glDeleteQueries(int n, IntBuffer ids) {
		delegate.glDeleteQueries(n, ids);
	}

	public void glDeleteQueriesARB(int n, int[] ids, int ids_offset) {
		delegate.glDeleteQueriesARB(n, ids, ids_offset);
	}

	public void glDeleteQueriesARB(int n, IntBuffer ids) {
		delegate.glDeleteQueriesARB(n, ids);
	}

	public void glDeleteRenderbuffersEXT(int n, int[] ids, int ids_offset) {
		delegate.glDeleteRenderbuffersEXT(n, ids, ids_offset);
	}

	public void glDeleteRenderbuffersEXT(int n, IntBuffer ids) {
		delegate.glDeleteRenderbuffersEXT(n, ids);
	}

	public void glDeleteShader(int mode) {
		delegate.glDeleteShader(mode);
	}

	public void glDeleteTextures(int n, int[] textures, int textures_offset) {
		delegate.glDeleteTextures(n, textures, textures_offset);
	}

	public void glDeleteTextures(int n, IntBuffer textures) {
		delegate.glDeleteTextures(n, textures);
	}

	public void glDeleteVertexArraysAPPLE(int n, int[] ids, int ids_offset) {
		delegate.glDeleteVertexArraysAPPLE(n, ids, ids_offset);
	}

	public void glDeleteVertexArraysAPPLE(int n, IntBuffer ids) {
		delegate.glDeleteVertexArraysAPPLE(n, ids);
	}

	public void glDeleteVertexShaderEXT(int mode) {
		delegate.glDeleteVertexShaderEXT(mode);
	}

	public void glDepthBoundsdNV(double zmin, double zmax) {
		delegate.glDepthBoundsdNV(zmin, zmax);
	}

	public void glDepthBoundsEXT(double x, double y) {
		delegate.glDepthBoundsEXT(x, y);
	}

	public void glDepthFunc(int func) {
		delegate.glDepthFunc(func);
	}

	public void glDepthMask(boolean flag) {
		delegate.glDepthMask(flag);
	}

	public void glDepthRange(double near_val, double far_val) {
		delegate.glDepthRange(near_val, far_val);
	}

	public void glDepthRangedNV(double near, double far) {
		delegate.glDepthRangedNV(near, far);
	}

	public void glDetachObjectARB(int target, int id) {
		delegate.glDetachObjectARB(target, id);
	}

	public void glDetachShader(int target, int id) {
		delegate.glDetachShader(target, id);
	}

	public void glDetailTexFuncSGIS(int target, int n, float[] points,
			int points_offset) {
		delegate.glDetailTexFuncSGIS(target, n, points, points_offset);
	}

	public void glDetailTexFuncSGIS(int target, int n, FloatBuffer points) {
		delegate.glDetailTexFuncSGIS(target, n, points);
	}

	public void glDisable(int cap) {
		delegate.glDisable(cap);
	}

	public void glDisableClientState(int cap) {
		delegate.glDisableClientState(cap);
	}

	public void glDisableIndexedEXT(int target, int index) {
		delegate.glDisableIndexedEXT(target, index);
	}

	public void glDisableVariantClientStateEXT(int mode) {
		delegate.glDisableVariantClientStateEXT(mode);
	}

	public void glDisableVertexAttribAPPLE(int index, int pname) {
		delegate.glDisableVertexAttribAPPLE(index, pname);
	}

	public void glDisableVertexAttribArray(int mode) {
		delegate.glDisableVertexAttribArray(mode);
	}

	public void glDisableVertexAttribArrayARB(int mode) {
		delegate.glDisableVertexAttribArrayARB(mode);
	}

	public void glDrawArrays(int mode, int first, int count) {
		delegate.glDrawArrays(mode, first, count);
	}

	public void glDrawArraysInstancedEXT(int mode, int start, int count,
			int primcount) {
		delegate.glDrawArraysInstancedEXT(mode, start, count, primcount);
	}

	public void glDrawBuffer(int mode) {
		delegate.glDrawBuffer(mode);
	}

	public void glDrawBufferRegion(int region, int x, int y, int width,
			int height, int dest, int dest2) {
		delegate.glDrawBufferRegion(region, x, y, width, height, dest, dest2);
	}

	public void glDrawBuffers(int n, int[] ids, int ids_offset) {
		delegate.glDrawBuffers(n, ids, ids_offset);
	}

	public void glDrawBuffers(int n, IntBuffer ids) {
		delegate.glDrawBuffers(n, ids);
	}

	public void glDrawBuffersARB(int n, int[] ids, int ids_offset) {
		delegate.glDrawBuffersARB(n, ids, ids_offset);
	}

	public void glDrawBuffersARB(int n, IntBuffer ids) {
		delegate.glDrawBuffersARB(n, ids);
	}

	public void glDrawBuffersATI(int n, int[] ids, int ids_offset) {
		delegate.glDrawBuffersATI(n, ids, ids_offset);
	}

	public void glDrawBuffersATI(int n, IntBuffer ids) {
		delegate.glDrawBuffersATI(n, ids);
	}

	public void glDrawElementArrayAPPLE(int target, int s, int t) {
		delegate.glDrawElementArrayAPPLE(target, s, t);
	}

	public void glDrawElementArrayATI(int target, int s) {
		delegate.glDrawElementArrayATI(target, s);
	}

	public void glDrawElements(int mode, int count, int type, Buffer indices) {
		delegate.glDrawElements(mode, count, type, indices);
	}

	public void glDrawElements(int mode, int count, int type,
			long indices_buffer_offset) {
		delegate.glDrawElements(mode, count, type, indices_buffer_offset);
	}

	public void glDrawElementsInstancedEXT(int mode, int count, int type,
			Buffer indices, int primcount) {
		delegate.glDrawElementsInstancedEXT(mode, count, type, indices,
				primcount);
	}

	public void glDrawMeshArraysSUN(int target, int s, int t, int r) {
		delegate.glDrawMeshArraysSUN(target, s, t, r);
	}

	public void glDrawPixels(int width, int height, int format, int type,
			Buffer pixels) {
		delegate.glDrawPixels(width, height, format, type, pixels);
	}

	public void glDrawPixels(int width, int height, int format, int type,
			long pixels_buffer_offset) {
		delegate
				.glDrawPixels(width, height, format, type, pixels_buffer_offset);
	}

	public void glDrawRangeElementArrayAPPLE(int mode, int start, int end,
			int first, int count) {
		delegate.glDrawRangeElementArrayAPPLE(mode, start, end, first, count);
	}

	public void glDrawRangeElementArrayATI(int mode, int start, int end,
			int count) {
		delegate.glDrawRangeElementArrayATI(mode, start, end, count);
	}

	public void glDrawRangeElements(int mode, int start, int end, int count,
			int type, Buffer indices) {
		delegate.glDrawRangeElements(mode, start, end, count, type, indices);
	}

	public void glDrawRangeElements(int mode, int start, int end, int count,
			int type, long indices_buffer_offset) {
		delegate.glDrawRangeElements(mode, start, end, count, type,
				indices_buffer_offset);
	}

	public void glEdgeFlag(boolean flag) {
		delegate.glEdgeFlag(flag);
	}

	public void glEdgeFlagPointer(int stride, Buffer ptr) {
		delegate.glEdgeFlagPointer(stride, ptr);
	}

	public void glEdgeFlagPointer(int stride, long ptr_buffer_offset) {
		delegate.glEdgeFlagPointer(stride, ptr_buffer_offset);
	}

	public void glEdgeFlagv(byte[] flag, int flag_offset) {
		delegate.glEdgeFlagv(flag, flag_offset);
	}

	public void glEdgeFlagv(ByteBuffer flag) {
		delegate.glEdgeFlagv(flag);
	}

	public void glElementPointerAPPLE(int pname, Buffer params) {
		delegate.glElementPointerAPPLE(pname, params);
	}

	public void glElementPointerATI(int pname, Buffer params) {
		delegate.glElementPointerATI(pname, params);
	}

	public void glElementPointerATI(int pname, long params_buffer_offset) {
		delegate.glElementPointerATI(pname, params_buffer_offset);
	}

	public void glEnable(int cap) {
		delegate.glEnable(cap);
	}

	public void glEnableClientState(int cap) {
		delegate.glEnableClientState(cap);
	}

	public void glEnableIndexedEXT(int target, int index) {
		delegate.glEnableIndexedEXT(target, index);
	}

	public void glEnableVariantClientStateEXT(int mode) {
		delegate.glEnableVariantClientStateEXT(mode);
	}

	public void glEnableVertexAttribAPPLE(int index, int pname) {
		delegate.glEnableVertexAttribAPPLE(index, pname);
	}

	public void glEnableVertexAttribArray(int mode) {
		delegate.glEnableVertexAttribArray(mode);
	}

	public void glEnableVertexAttribArrayARB(int mode) {
		delegate.glEnableVertexAttribArrayARB(mode);
	}

	public void glEnd() {
		delegate.glEnd();
	}

	public void glEndFragmentShaderATI() {
		delegate.glEndFragmentShaderATI();
	}

	public void glEndList() {
		delegate.glEndList();
	}

	public void glEndOcclusionQueryNV() {
		delegate.glEndOcclusionQueryNV();
	}

	public void glEndQuery(int mode) {
		delegate.glEndQuery(mode);
	}

	public void glEndQueryARB(int mode) {
		delegate.glEndQueryARB(mode);
	}

	public void glEndTransformFeedbackNV() {
		delegate.glEndTransformFeedbackNV();
	}

	public void glEndVertexShaderEXT() {
		delegate.glEndVertexShaderEXT();
	}

	public void glEvalCoord1d(double u) {
		delegate.glEvalCoord1d(u);
	}

	public void glEvalCoord1dv(double[] u, int u_offset) {
		delegate.glEvalCoord1dv(u, u_offset);
	}

	public void glEvalCoord1dv(DoubleBuffer u) {
		delegate.glEvalCoord1dv(u);
	}

	public void glEvalCoord1f(float u) {
		delegate.glEvalCoord1f(u);
	}

	public void glEvalCoord1fv(float[] u, int u_offset) {
		delegate.glEvalCoord1fv(u, u_offset);
	}

	public void glEvalCoord1fv(FloatBuffer u) {
		delegate.glEvalCoord1fv(u);
	}

	public void glEvalCoord2d(double u, double v) {
		delegate.glEvalCoord2d(u, v);
	}

	public void glEvalCoord2dv(double[] u, int u_offset) {
		delegate.glEvalCoord2dv(u, u_offset);
	}

	public void glEvalCoord2dv(DoubleBuffer u) {
		delegate.glEvalCoord2dv(u);
	}

	public void glEvalCoord2f(float u, float v) {
		delegate.glEvalCoord2f(u, v);
	}

	public void glEvalCoord2fv(float[] u, int u_offset) {
		delegate.glEvalCoord2fv(u, u_offset);
	}

	public void glEvalCoord2fv(FloatBuffer u) {
		delegate.glEvalCoord2fv(u);
	}

	public void glEvalMapsNV(int target, int id) {
		delegate.glEvalMapsNV(target, id);
	}

	public void glEvalMesh1(int mode, int i1, int i2) {
		delegate.glEvalMesh1(mode, i1, i2);
	}

	public void glEvalMesh2(int mode, int i1, int i2, int j1, int j2) {
		delegate.glEvalMesh2(mode, i1, i2, j1, j2);
	}

	public void glEvalPoint1(int i) {
		delegate.glEvalPoint1(i);
	}

	public void glEvalPoint2(int i, int j) {
		delegate.glEvalPoint2(i, j);
	}

	public void glExecuteProgramNV(int target, int pname, float[] params,
			int params_offset) {
		delegate.glExecuteProgramNV(target, pname, params, params_offset);
	}

	public void glExecuteProgramNV(int target, int pname, FloatBuffer params) {
		delegate.glExecuteProgramNV(target, pname, params);
	}

	public void glExtractComponentEXT(int red, int green, int blue) {
		delegate.glExtractComponentEXT(red, green, blue);
	}

	public void glFeedbackBuffer(int size, int type, FloatBuffer buffer) {
		delegate.glFeedbackBuffer(size, type, buffer);
	}

	public void glFinalCombinerInputNV(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha) {
		delegate.glFinalCombinerInputNV(sfactorRGB, dfactorRGB, sfactorAlpha,
				dfactorAlpha);
	}

	public void glFinish() {
		delegate.glFinish();
	}

	public int glFinishAsyncSGIX(int[] markerp, int markerp_offset) {
		return delegate.glFinishAsyncSGIX(markerp, markerp_offset);
	}

	public int glFinishAsyncSGIX(IntBuffer markerp) {
		return delegate.glFinishAsyncSGIX(markerp);
	}

	public void glFinishFenceAPPLE(int mode) {
		delegate.glFinishFenceAPPLE(mode);
	}

	public void glFinishFenceNV(int mode) {
		delegate.glFinishFenceNV(mode);
	}

	public void glFinishObjectAPPLE(int target, int s) {
		delegate.glFinishObjectAPPLE(target, s);
	}

	public void glFinishRenderAPPLE() {
		delegate.glFinishRenderAPPLE();
	}

	public void glFinishTextureSUNX() {
		delegate.glFinishTextureSUNX();
	}

	public void glFlush() {
		delegate.glFlush();
	}

	public void glFlushMappedBufferRangeAPPLE(int target, int s, int t) {
		delegate.glFlushMappedBufferRangeAPPLE(target, s, t);
	}

	public void glFlushPixelDataRangeNV(int mode) {
		delegate.glFlushPixelDataRangeNV(mode);
	}

	public void glFlushRasterSGIX() {
		delegate.glFlushRasterSGIX();
	}

	public void glFlushRenderAPPLE() {
		delegate.glFlushRenderAPPLE();
	}

	public void glFlushVertexArrayRangeAPPLE(int length, Buffer pointer) {
		delegate.glFlushVertexArrayRangeAPPLE(length, pointer);
	}

	public void glFlushVertexArrayRangeNV() {
		delegate.glFlushVertexArrayRangeNV();
	}

	public void glFogCoordd(double coord) {
		delegate.glFogCoordd(coord);
	}

	public void glFogCoorddEXT(double coord) {
		delegate.glFogCoorddEXT(coord);
	}

	public void glFogCoorddv(double[] m, int m_offset) {
		delegate.glFogCoorddv(m, m_offset);
	}

	public void glFogCoorddv(DoubleBuffer m) {
		delegate.glFogCoorddv(m);
	}

	public void glFogCoorddvEXT(double[] m, int m_offset) {
		delegate.glFogCoorddvEXT(m, m_offset);
	}

	public void glFogCoorddvEXT(DoubleBuffer m) {
		delegate.glFogCoorddvEXT(m);
	}

	public void glFogCoordf(float coord) {
		delegate.glFogCoordf(coord);
	}

	public void glFogCoordfEXT(float coord) {
		delegate.glFogCoordfEXT(coord);
	}

	public void glFogCoordfv(float[] m, int m_offset) {
		delegate.glFogCoordfv(m, m_offset);
	}

	public void glFogCoordfv(FloatBuffer m) {
		delegate.glFogCoordfv(m);
	}

	public void glFogCoordfvEXT(float[] m, int m_offset) {
		delegate.glFogCoordfvEXT(m, m_offset);
	}

	public void glFogCoordfvEXT(FloatBuffer m) {
		delegate.glFogCoordfvEXT(m);
	}

	public void glFogCoordhNV(short factor) {
		delegate.glFogCoordhNV(factor);
	}

	public void glFogCoordhvNV(short[] v, int v_offset) {
		delegate.glFogCoordhvNV(v, v_offset);
	}

	public void glFogCoordhvNV(ShortBuffer v) {
		delegate.glFogCoordhvNV(v);
	}

	public void glFogCoordPointer(int type, int stride, Buffer pointer) {
		delegate.glFogCoordPointer(type, stride, pointer);
	}

	public void glFogCoordPointer(int type, int stride,
			long pointer_buffer_offset) {
		delegate.glFogCoordPointer(type, stride, pointer_buffer_offset);
	}

	public void glFogCoordPointerEXT(int type, int stride, Buffer pointer) {
		delegate.glFogCoordPointerEXT(type, stride, pointer);
	}

	public void glFogCoordPointerEXT(int type, int stride,
			long pointer_buffer_offset) {
		delegate.glFogCoordPointerEXT(type, stride, pointer_buffer_offset);
	}

	public void glFogf(int pname, float param) {
		delegate.glFogf(pname, param);
	}

	public void glFogFuncSGIS(int size, float[] weights, int weights_offset) {
		delegate.glFogFuncSGIS(size, weights, weights_offset);
	}

	public void glFogFuncSGIS(int size, FloatBuffer weights) {
		delegate.glFogFuncSGIS(size, weights);
	}

	public void glFogfv(int pname, float[] params, int params_offset) {
		delegate.glFogfv(pname, params, params_offset);
	}

	public void glFogfv(int pname, FloatBuffer params) {
		delegate.glFogfv(pname, params);
	}

	public void glFogi(int pname, int param) {
		delegate.glFogi(pname, param);
	}

	public void glFogiv(int pname, int[] params, int params_offset) {
		delegate.glFogiv(pname, params, params_offset);
	}

	public void glFogiv(int pname, IntBuffer params) {
		delegate.glFogiv(pname, params);
	}

	public void glFragmentColorMaterialSGIX(int target, int id) {
		delegate.glFragmentColorMaterialSGIX(target, id);
	}

	public void glFragmentLightfSGIX(int target, int pname, float params) {
		delegate.glFragmentLightfSGIX(target, pname, params);
	}

	public void glFragmentLightfvSGIX(int target, int pname, float[] params,
			int params_offset) {
		delegate.glFragmentLightfvSGIX(target, pname, params, params_offset);
	}

	public void glFragmentLightfvSGIX(int target, int pname, FloatBuffer params) {
		delegate.glFragmentLightfvSGIX(target, pname, params);
	}

	public void glFragmentLightiSGIX(int target, int pname, int params) {
		delegate.glFragmentLightiSGIX(target, pname, params);
	}

	public void glFragmentLightivSGIX(int target, int pname, int[] params,
			int params_offset) {
		delegate.glFragmentLightivSGIX(target, pname, params, params_offset);
	}

	public void glFragmentLightivSGIX(int target, int pname, IntBuffer params) {
		delegate.glFragmentLightivSGIX(target, pname, params);
	}

	public void glFragmentLightModelfSGIX(int target, float s) {
		delegate.glFragmentLightModelfSGIX(target, s);
	}

	public void glFragmentLightModelfvSGIX(int target, float[] v, int v_offset) {
		delegate.glFragmentLightModelfvSGIX(target, v, v_offset);
	}

	public void glFragmentLightModelfvSGIX(int target, FloatBuffer v) {
		delegate.glFragmentLightModelfvSGIX(target, v);
	}

	public void glFragmentLightModeliSGIX(int target, int s) {
		delegate.glFragmentLightModeliSGIX(target, s);
	}

	public void glFragmentLightModelivSGIX(int target, int[] v, int v_offset) {
		delegate.glFragmentLightModelivSGIX(target, v, v_offset);
	}

	public void glFragmentLightModelivSGIX(int target, IntBuffer v) {
		delegate.glFragmentLightModelivSGIX(target, v);
	}

	public void glFragmentMaterialfSGIX(int target, int pname, float params) {
		delegate.glFragmentMaterialfSGIX(target, pname, params);
	}

	public void glFragmentMaterialfvSGIX(int target, int pname, float[] params,
			int params_offset) {
		delegate.glFragmentMaterialfvSGIX(target, pname, params, params_offset);
	}

	public void glFragmentMaterialfvSGIX(int target, int pname,
			FloatBuffer params) {
		delegate.glFragmentMaterialfvSGIX(target, pname, params);
	}

	public void glFragmentMaterialiSGIX(int target, int pname, int params) {
		delegate.glFragmentMaterialiSGIX(target, pname, params);
	}

	public void glFragmentMaterialivSGIX(int target, int pname, int[] params,
			int params_offset) {
		delegate.glFragmentMaterialivSGIX(target, pname, params, params_offset);
	}

	public void glFragmentMaterialivSGIX(int target, int pname, IntBuffer params) {
		delegate.glFragmentMaterialivSGIX(target, pname, params);
	}

	public void glFramebufferRenderbufferEXT(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha) {
		delegate.glFramebufferRenderbufferEXT(sfactorRGB, dfactorRGB,
				sfactorAlpha, dfactorAlpha);
	}

	public void glFramebufferTexture1DEXT(int target, int attachment,
			int textarget, int texture, int level) {
		delegate.glFramebufferTexture1DEXT(target, attachment, textarget,
				texture, level);
	}

	public void glFramebufferTexture2DEXT(int target, int attachment,
			int textarget, int texture, int level) {
		delegate.glFramebufferTexture2DEXT(target, attachment, textarget,
				texture, level);
	}

	public void glFramebufferTexture3DEXT(int target, int attachment,
			int textarget, int texture, int level, int zoffset) {
		delegate.glFramebufferTexture3DEXT(target, attachment, textarget,
				texture, level, zoffset);
	}

	public void glFramebufferTextureEXT(int target, int attachment,
			int texture, int level) {
		delegate.glFramebufferTextureEXT(target, attachment, texture, level);
	}

	public void glFramebufferTextureFaceEXT(int target, int attachment,
			int texture, int level, int face) {
		delegate.glFramebufferTextureFaceEXT(target, attachment, texture,
				level, face);
	}

	public void glFramebufferTextureLayerEXT(int target, int attachment,
			int texture, int level, int layer) {
		delegate.glFramebufferTextureLayerEXT(target, attachment, texture,
				level, layer);
	}

	public void glFrameZoomSGIX(int count) {
		delegate.glFrameZoomSGIX(count);
	}

	public void glFreeObjectBufferATI(int mode) {
		delegate.glFreeObjectBufferATI(mode);
	}

	public void glFrontFace(int mode) {
		delegate.glFrontFace(mode);
	}

	public void glFrustum(double left, double right, double bottom, double top,
			double near_val, double far_val) {
		delegate.glFrustum(left, right, bottom, top, near_val, far_val);
	}

	public int glGenAsyncMarkersSGIX(int range) {
		return delegate.glGenAsyncMarkersSGIX(range);
	}

	public void glGenBuffers(int n, int[] ids, int ids_offset) {
		delegate.glGenBuffers(n, ids, ids_offset);
	}

	public void glGenBuffers(int n, IntBuffer ids) {
		delegate.glGenBuffers(n, ids);
	}

	public void glGenBuffersARB(int n, int[] ids, int ids_offset) {
		delegate.glGenBuffersARB(n, ids, ids_offset);
	}

	public void glGenBuffersARB(int n, IntBuffer ids) {
		delegate.glGenBuffersARB(n, ids);
	}

	public void glGenerateMipmapEXT(int mode) {
		delegate.glGenerateMipmapEXT(mode);
	}

	public void glGenFencesAPPLE(int n, int[] ids, int ids_offset) {
		delegate.glGenFencesAPPLE(n, ids, ids_offset);
	}

	public void glGenFencesAPPLE(int n, IntBuffer ids) {
		delegate.glGenFencesAPPLE(n, ids);
	}

	public void glGenFencesNV(int n, int[] ids, int ids_offset) {
		delegate.glGenFencesNV(n, ids, ids_offset);
	}

	public void glGenFencesNV(int n, IntBuffer ids) {
		delegate.glGenFencesNV(n, ids);
	}

	public int glGenFragmentShadersATI(int type) {
		return delegate.glGenFragmentShadersATI(type);
	}

	public void glGenFramebuffersEXT(int n, int[] ids, int ids_offset) {
		delegate.glGenFramebuffersEXT(n, ids, ids_offset);
	}

	public void glGenFramebuffersEXT(int n, IntBuffer ids) {
		delegate.glGenFramebuffersEXT(n, ids);
	}

	public int glGenLists(int range) {
		return delegate.glGenLists(range);
	}

	public void glGenOcclusionQueriesNV(int n, int[] ids, int ids_offset) {
		delegate.glGenOcclusionQueriesNV(n, ids, ids_offset);
	}

	public void glGenOcclusionQueriesNV(int n, IntBuffer ids) {
		delegate.glGenOcclusionQueriesNV(n, ids);
	}

	public void glGenProgramsARB(int n, int[] ids, int ids_offset) {
		delegate.glGenProgramsARB(n, ids, ids_offset);
	}

	public void glGenProgramsARB(int n, IntBuffer ids) {
		delegate.glGenProgramsARB(n, ids);
	}

	public void glGenProgramsNV(int n, int[] ids, int ids_offset) {
		delegate.glGenProgramsNV(n, ids, ids_offset);
	}

	public void glGenProgramsNV(int n, IntBuffer ids) {
		delegate.glGenProgramsNV(n, ids);
	}

	public void glGenQueries(int n, int[] ids, int ids_offset) {
		delegate.glGenQueries(n, ids, ids_offset);
	}

	public void glGenQueries(int n, IntBuffer ids) {
		delegate.glGenQueries(n, ids);
	}

	public void glGenQueriesARB(int n, int[] ids, int ids_offset) {
		delegate.glGenQueriesARB(n, ids, ids_offset);
	}

	public void glGenQueriesARB(int n, IntBuffer ids) {
		delegate.glGenQueriesARB(n, ids);
	}

	public void glGenRenderbuffersEXT(int n, int[] ids, int ids_offset) {
		delegate.glGenRenderbuffersEXT(n, ids, ids_offset);
	}

	public void glGenRenderbuffersEXT(int n, IntBuffer ids) {
		delegate.glGenRenderbuffersEXT(n, ids);
	}

	public int glGenSymbolsEXT(int datatype, int storagetype, int range,
			int components) {
		return delegate.glGenSymbolsEXT(datatype, storagetype, range,
				components);
	}

	public void glGenTextures(int n, int[] textures, int textures_offset) {
		delegate.glGenTextures(n, textures, textures_offset);
	}

	public void glGenTextures(int n, IntBuffer textures) {
		delegate.glGenTextures(n, textures);
	}

	public void glGenVertexArraysAPPLE(int n, int[] ids, int ids_offset) {
		delegate.glGenVertexArraysAPPLE(n, ids, ids_offset);
	}

	public void glGenVertexArraysAPPLE(int n, IntBuffer ids) {
		delegate.glGenVertexArraysAPPLE(n, ids);
	}

	public int glGenVertexShadersEXT(int type) {
		return delegate.glGenVertexShadersEXT(type);
	}

	public void glGetActiveAttrib(int program, int index, int bufSize,
			int[] length, int length_offset, int[] size, int size_offset,
			int[] type, int type_offset, byte[] name, int name_offset) {
		delegate.glGetActiveAttrib(program, index, bufSize, length,
				length_offset, size, size_offset, type, type_offset, name,
				name_offset);
	}

	public void glGetActiveAttrib(int program, int index, int bufSize,
			IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		delegate.glGetActiveAttrib(program, index, bufSize, length, size, type,
				name);
	}

	public void glGetActiveAttribARB(int program, int index, int bufSize,
			int[] length, int length_offset, int[] size, int size_offset,
			int[] type, int type_offset, byte[] name, int name_offset) {
		delegate.glGetActiveAttribARB(program, index, bufSize, length,
				length_offset, size, size_offset, type, type_offset, name,
				name_offset);
	}

	public void glGetActiveAttribARB(int program, int index, int bufSize,
			IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		delegate.glGetActiveAttribARB(program, index, bufSize, length, size,
				type, name);
	}

	public void glGetActiveUniform(int program, int index, int bufSize,
			int[] length, int length_offset, int[] size, int size_offset,
			int[] type, int type_offset, byte[] name, int name_offset) {
		delegate.glGetActiveUniform(program, index, bufSize, length,
				length_offset, size, size_offset, type, type_offset, name,
				name_offset);
	}

	public void glGetActiveUniform(int program, int index, int bufSize,
			IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		delegate.glGetActiveUniform(program, index, bufSize, length, size,
				type, name);
	}

	public void glGetActiveUniformARB(int program, int index, int bufSize,
			int[] length, int length_offset, int[] size, int size_offset,
			int[] type, int type_offset, byte[] name, int name_offset) {
		delegate.glGetActiveUniformARB(program, index, bufSize, length,
				length_offset, size, size_offset, type, type_offset, name,
				name_offset);
	}

	public void glGetActiveUniformARB(int program, int index, int bufSize,
			IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		delegate.glGetActiveUniformARB(program, index, bufSize, length, size,
				type, name);
	}

	public void glGetActiveVaryingNV(int program, int index, int bufSize,
			int[] length, int length_offset, int[] size, int size_offset,
			int[] type, int type_offset, byte[] name, int name_offset) {
		delegate.glGetActiveVaryingNV(program, index, bufSize, length,
				length_offset, size, size_offset, type, type_offset, name,
				name_offset);
	}

	public void glGetActiveVaryingNV(int program, int index, int bufSize,
			IntBuffer length, IntBuffer size, IntBuffer type, ByteBuffer name) {
		delegate.glGetActiveVaryingNV(program, index, bufSize, length, size,
				type, name);
	}

	public void glGetArrayObjectfvATI(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetArrayObjectfvATI(target, pname, params, params_offset);
	}

	public void glGetArrayObjectfvATI(int target, int pname, FloatBuffer params) {
		delegate.glGetArrayObjectfvATI(target, pname, params);
	}

	public void glGetArrayObjectivATI(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetArrayObjectivATI(target, pname, params, params_offset);
	}

	public void glGetArrayObjectivATI(int target, int pname, IntBuffer params) {
		delegate.glGetArrayObjectivATI(target, pname, params);
	}

	public void glGetAttachedObjectsARB(int program, int maxCount, int[] count,
			int count_offset, int[] obj, int obj_offset) {
		delegate.glGetAttachedObjectsARB(program, maxCount, count,
				count_offset, obj, obj_offset);
	}

	public void glGetAttachedObjectsARB(int program, int maxCount,
			IntBuffer count, IntBuffer obj) {
		delegate.glGetAttachedObjectsARB(program, maxCount, count, obj);
	}

	public void glGetAttachedShaders(int program, int maxCount, int[] count,
			int count_offset, int[] obj, int obj_offset) {
		delegate.glGetAttachedShaders(program, maxCount, count, count_offset,
				obj, obj_offset);
	}

	public void glGetAttachedShaders(int program, int maxCount,
			IntBuffer count, IntBuffer obj) {
		delegate.glGetAttachedShaders(program, maxCount, count, obj);
	}

	public int glGetAttribLocation(int program, String name) {
		return delegate.glGetAttribLocation(program, name);
	}

	public int glGetAttribLocationARB(int program, String name) {
		return delegate.glGetAttribLocationARB(program, name);
	}

	public void glGetBooleanIndexedvEXT(int target, int index, byte[] data,
			int data_offset) {
		delegate.glGetBooleanIndexedvEXT(target, index, data, data_offset);
	}

	public void glGetBooleanIndexedvEXT(int target, int index, ByteBuffer data) {
		delegate.glGetBooleanIndexedvEXT(target, index, data);
	}

	public void glGetBooleanv(int pname, byte[] params, int params_offset) {
		delegate.glGetBooleanv(pname, params, params_offset);
	}

	public void glGetBooleanv(int pname, ByteBuffer params) {
		delegate.glGetBooleanv(pname, params);
	}

	public void glGetBufferParameteriv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetBufferParameteriv(target, pname, params, params_offset);
	}

	public void glGetBufferParameteriv(int target, int pname, IntBuffer params) {
		delegate.glGetBufferParameteriv(target, pname, params);
	}

	public void glGetBufferParameterivARB(int target, int pname, int[] params,
			int params_offset) {
		delegate
				.glGetBufferParameterivARB(target, pname, params, params_offset);
	}

	public void glGetBufferParameterivARB(int target, int pname,
			IntBuffer params) {
		delegate.glGetBufferParameterivARB(target, pname, params);
	}

	public void glGetBufferSubData(int target, int offset, int size, Buffer data) {
		delegate.glGetBufferSubData(target, offset, size, data);
	}

	public void glGetBufferSubDataARB(int target, int offset, int size,
			Buffer data) {
		delegate.glGetBufferSubDataARB(target, offset, size, data);
	}

	public void glGetClipPlane(int plane, double[] equation, int equation_offset) {
		delegate.glGetClipPlane(plane, equation, equation_offset);
	}

	public void glGetClipPlane(int plane, DoubleBuffer equation) {
		delegate.glGetClipPlane(plane, equation);
	}

	public void glGetColorTable(int target, int format, int type, Buffer table) {
		delegate.glGetColorTable(target, format, type, table);
	}

	public void glGetColorTable(int target, int format, int type,
			long table_buffer_offset) {
		delegate.glGetColorTable(target, format, type, table_buffer_offset);
	}

	public void glGetColorTableEXT(int target, int format, int type,
			Buffer table) {
		delegate.glGetColorTableEXT(target, format, type, table);
	}

	public void glGetColorTableParameterfv(int target, int pname,
			float[] params, int params_offset) {
		delegate.glGetColorTableParameterfv(target, pname, params,
				params_offset);
	}

	public void glGetColorTableParameterfv(int target, int pname,
			FloatBuffer params) {
		delegate.glGetColorTableParameterfv(target, pname, params);
	}

	public void glGetColorTableParameterfvEXT(int target, int pname,
			float[] params, int params_offset) {
		delegate.glGetColorTableParameterfvEXT(target, pname, params,
				params_offset);
	}

	public void glGetColorTableParameterfvEXT(int target, int pname,
			FloatBuffer params) {
		delegate.glGetColorTableParameterfvEXT(target, pname, params);
	}

	public void glGetColorTableParameteriv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetColorTableParameteriv(target, pname, params,
				params_offset);
	}

	public void glGetColorTableParameteriv(int target, int pname,
			IntBuffer params) {
		delegate.glGetColorTableParameteriv(target, pname, params);
	}

	public void glGetColorTableParameterivEXT(int target, int pname,
			int[] params, int params_offset) {
		delegate.glGetColorTableParameterivEXT(target, pname, params,
				params_offset);
	}

	public void glGetColorTableParameterivEXT(int target, int pname,
			IntBuffer params) {
		delegate.glGetColorTableParameterivEXT(target, pname, params);
	}

	public void glGetCombinerInputParameterfvNV(int stage, int portion,
			int variable, int pname, float[] params, int params_offset) {
		delegate.glGetCombinerInputParameterfvNV(stage, portion, variable,
				pname, params, params_offset);
	}

	public void glGetCombinerInputParameterfvNV(int stage, int portion,
			int variable, int pname, FloatBuffer params) {
		delegate.glGetCombinerInputParameterfvNV(stage, portion, variable,
				pname, params);
	}

	public void glGetCombinerInputParameterivNV(int stage, int portion,
			int variable, int pname, int[] params, int params_offset) {
		delegate.glGetCombinerInputParameterivNV(stage, portion, variable,
				pname, params, params_offset);
	}

	public void glGetCombinerInputParameterivNV(int stage, int portion,
			int variable, int pname, IntBuffer params) {
		delegate.glGetCombinerInputParameterivNV(stage, portion, variable,
				pname, params);
	}

	public void glGetCombinerOutputParameterfvNV(int stage, int portion,
			int pname, float[] params, int params_offset) {
		delegate.glGetCombinerOutputParameterfvNV(stage, portion, pname,
				params, params_offset);
	}

	public void glGetCombinerOutputParameterfvNV(int stage, int portion,
			int pname, FloatBuffer params) {
		delegate
				.glGetCombinerOutputParameterfvNV(stage, portion, pname, params);
	}

	public void glGetCombinerOutputParameterivNV(int stage, int portion,
			int pname, int[] params, int params_offset) {
		delegate.glGetCombinerOutputParameterivNV(stage, portion, pname,
				params, params_offset);
	}

	public void glGetCombinerOutputParameterivNV(int stage, int portion,
			int pname, IntBuffer params) {
		delegate
				.glGetCombinerOutputParameterivNV(stage, portion, pname, params);
	}

	public void glGetCombinerStageParameterfvNV(int target, int pname,
			float[] params, int params_offset) {
		delegate.glGetCombinerStageParameterfvNV(target, pname, params,
				params_offset);
	}

	public void glGetCombinerStageParameterfvNV(int target, int pname,
			FloatBuffer params) {
		delegate.glGetCombinerStageParameterfvNV(target, pname, params);
	}

	public void glGetCompressedTexImage(int target, int level, Buffer img) {
		delegate.glGetCompressedTexImage(target, level, img);
	}

	public void glGetCompressedTexImage(int target, int level,
			long img_buffer_offset) {
		delegate.glGetCompressedTexImage(target, level, img_buffer_offset);
	}

	public void glGetConvolutionFilter(int target, int format, int type,
			Buffer table) {
		delegate.glGetConvolutionFilter(target, format, type, table);
	}

	public void glGetConvolutionFilter(int target, int format, int type,
			long table_buffer_offset) {
		delegate.glGetConvolutionFilter(target, format, type,
				table_buffer_offset);
	}

	public void glGetConvolutionParameterfv(int target, int pname,
			float[] params, int params_offset) {
		delegate.glGetConvolutionParameterfv(target, pname, params,
				params_offset);
	}

	public void glGetConvolutionParameterfv(int target, int pname,
			FloatBuffer params) {
		delegate.glGetConvolutionParameterfv(target, pname, params);
	}

	public void glGetConvolutionParameteriv(int target, int pname,
			int[] params, int params_offset) {
		delegate.glGetConvolutionParameteriv(target, pname, params,
				params_offset);
	}

	public void glGetConvolutionParameteriv(int target, int pname,
			IntBuffer params) {
		delegate.glGetConvolutionParameteriv(target, pname, params);
	}

	public void glGetDetailTexFuncSGIS(int pname, float[] params,
			int params_offset) {
		delegate.glGetDetailTexFuncSGIS(pname, params, params_offset);
	}

	public void glGetDetailTexFuncSGIS(int pname, FloatBuffer params) {
		delegate.glGetDetailTexFuncSGIS(pname, params);
	}

	public void glGetDoublev(int pname, double[] params, int params_offset) {
		delegate.glGetDoublev(pname, params, params_offset);
	}

	public void glGetDoublev(int pname, DoubleBuffer params) {
		delegate.glGetDoublev(pname, params);
	}

	public int glGetError() {
		return delegate.glGetError();
	}

	public void glGetFenceivNV(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetFenceivNV(target, pname, params, params_offset);
	}

	public void glGetFenceivNV(int target, int pname, IntBuffer params) {
		delegate.glGetFenceivNV(target, pname, params);
	}

	public void glGetFinalCombinerInputParameterfvNV(int target, int pname,
			float[] params, int params_offset) {
		delegate.glGetFinalCombinerInputParameterfvNV(target, pname, params,
				params_offset);
	}

	public void glGetFinalCombinerInputParameterfvNV(int target, int pname,
			FloatBuffer params) {
		delegate.glGetFinalCombinerInputParameterfvNV(target, pname, params);
	}

	public void glGetFinalCombinerInputParameterivNV(int target, int pname,
			int[] params, int params_offset) {
		delegate.glGetFinalCombinerInputParameterivNV(target, pname, params,
				params_offset);
	}

	public void glGetFinalCombinerInputParameterivNV(int target, int pname,
			IntBuffer params) {
		delegate.glGetFinalCombinerInputParameterivNV(target, pname, params);
	}

	public void glGetFloatv(int pname, float[] params, int params_offset) {
		delegate.glGetFloatv(pname, params, params_offset);
	}

	public void glGetFloatv(int pname, FloatBuffer params) {
		delegate.glGetFloatv(pname, params);
	}

	public void glGetFogFuncSGIS(float[] points, int points_offset) {
		delegate.glGetFogFuncSGIS(points, points_offset);
	}

	public void glGetFogFuncSGIS(FloatBuffer points) {
		delegate.glGetFogFuncSGIS(points);
	}

	public int glGetFragDataLocationEXT(int program, byte[] name,
			int name_offset) {
		return delegate.glGetFragDataLocationEXT(program, name, name_offset);
	}

	public int glGetFragDataLocationEXT(int program, ByteBuffer name) {
		return delegate.glGetFragDataLocationEXT(program, name);
	}

	public void glGetFragmentLightfvSGIX(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetFragmentLightfvSGIX(target, pname, params, params_offset);
	}

	public void glGetFragmentLightfvSGIX(int target, int pname,
			FloatBuffer params) {
		delegate.glGetFragmentLightfvSGIX(target, pname, params);
	}

	public void glGetFragmentLightivSGIX(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetFragmentLightivSGIX(target, pname, params, params_offset);
	}

	public void glGetFragmentLightivSGIX(int target, int pname, IntBuffer params) {
		delegate.glGetFragmentLightivSGIX(target, pname, params);
	}

	public void glGetFragmentMaterialfvSGIX(int target, int pname,
			float[] params, int params_offset) {
		delegate.glGetFragmentMaterialfvSGIX(target, pname, params,
				params_offset);
	}

	public void glGetFragmentMaterialfvSGIX(int target, int pname,
			FloatBuffer params) {
		delegate.glGetFragmentMaterialfvSGIX(target, pname, params);
	}

	public void glGetFragmentMaterialivSGIX(int target, int pname,
			int[] params, int params_offset) {
		delegate.glGetFragmentMaterialivSGIX(target, pname, params,
				params_offset);
	}

	public void glGetFragmentMaterialivSGIX(int target, int pname,
			IntBuffer params) {
		delegate.glGetFragmentMaterialivSGIX(target, pname, params);
	}

	public void glGetFramebufferAttachmentParameterivEXT(int stage,
			int portion, int pname, int[] params, int params_offset) {
		delegate.glGetFramebufferAttachmentParameterivEXT(stage, portion,
				pname, params, params_offset);
	}

	public void glGetFramebufferAttachmentParameterivEXT(int stage,
			int portion, int pname, IntBuffer params) {
		delegate.glGetFramebufferAttachmentParameterivEXT(stage, portion,
				pname, params);
	}

	public int glGetHandleARB(int type) {
		return delegate.glGetHandleARB(type);
	}

	public void glGetHistogram(int target, boolean reset, int format, int type,
			Buffer values) {
		delegate.glGetHistogram(target, reset, format, type, values);
	}

	public void glGetHistogram(int target, boolean reset, int format, int type,
			long values_buffer_offset) {
		delegate.glGetHistogram(target, reset, format, type,
				values_buffer_offset);
	}

	public void glGetHistogramParameterfv(int target, int pname,
			float[] params, int params_offset) {
		delegate
				.glGetHistogramParameterfv(target, pname, params, params_offset);
	}

	public void glGetHistogramParameterfv(int target, int pname,
			FloatBuffer params) {
		delegate.glGetHistogramParameterfv(target, pname, params);
	}

	public void glGetHistogramParameteriv(int target, int pname, int[] params,
			int params_offset) {
		delegate
				.glGetHistogramParameteriv(target, pname, params, params_offset);
	}

	public void glGetHistogramParameteriv(int target, int pname,
			IntBuffer params) {
		delegate.glGetHistogramParameteriv(target, pname, params);
	}

	public void glGetImageTransformParameterfvHP(int target, int pname,
			float[] params, int params_offset) {
		delegate.glGetImageTransformParameterfvHP(target, pname, params,
				params_offset);
	}

	public void glGetImageTransformParameterfvHP(int target, int pname,
			FloatBuffer params) {
		delegate.glGetImageTransformParameterfvHP(target, pname, params);
	}

	public void glGetImageTransformParameterivHP(int target, int pname,
			int[] params, int params_offset) {
		delegate.glGetImageTransformParameterivHP(target, pname, params,
				params_offset);
	}

	public void glGetImageTransformParameterivHP(int target, int pname,
			IntBuffer params) {
		delegate.glGetImageTransformParameterivHP(target, pname, params);
	}

	public void glGetInfoLogARB(int program, int bufSize, int[] length,
			int length_offset, byte[] infoLog, int infoLog_offset) {
		delegate.glGetInfoLogARB(program, bufSize, length, length_offset,
				infoLog, infoLog_offset);
	}

	public void glGetInfoLogARB(int program, int bufSize, IntBuffer length,
			ByteBuffer infoLog) {
		delegate.glGetInfoLogARB(program, bufSize, length, infoLog);
	}

	public int glGetInstrumentsSGIX() {
		return delegate.glGetInstrumentsSGIX();
	}

	public void glGetIntegerIndexedvEXT(int target, int index, int[] data,
			int data_offset) {
		delegate.glGetIntegerIndexedvEXT(target, index, data, data_offset);
	}

	public void glGetIntegerIndexedvEXT(int target, int index, IntBuffer data) {
		delegate.glGetIntegerIndexedvEXT(target, index, data);
	}

	public void glGetIntegerv(int pname, int[] params, int params_offset) {
		delegate.glGetIntegerv(pname, params, params_offset);
	}

	public void glGetIntegerv(int pname, IntBuffer params) {
		delegate.glGetIntegerv(pname, params);
	}

	public void glGetInvariantBooleanvEXT(int id, int pname, byte[] program,
			int program_offset) {
		delegate.glGetInvariantBooleanvEXT(id, pname, program, program_offset);
	}

	public void glGetInvariantBooleanvEXT(int id, int pname, ByteBuffer program) {
		delegate.glGetInvariantBooleanvEXT(id, pname, program);
	}

	public void glGetInvariantFloatvEXT(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetInvariantFloatvEXT(target, pname, params, params_offset);
	}

	public void glGetInvariantFloatvEXT(int target, int pname,
			FloatBuffer params) {
		delegate.glGetInvariantFloatvEXT(target, pname, params);
	}

	public void glGetInvariantIntegervEXT(int target, int pname, int[] params,
			int params_offset) {
		delegate
				.glGetInvariantIntegervEXT(target, pname, params, params_offset);
	}

	public void glGetInvariantIntegervEXT(int target, int pname,
			IntBuffer params) {
		delegate.glGetInvariantIntegervEXT(target, pname, params);
	}

	public void glGetLightfv(int light, int pname, float[] params,
			int params_offset) {
		delegate.glGetLightfv(light, pname, params, params_offset);
	}

	public void glGetLightfv(int light, int pname, FloatBuffer params) {
		delegate.glGetLightfv(light, pname, params);
	}

	public void glGetLightiv(int light, int pname, int[] params,
			int params_offset) {
		delegate.glGetLightiv(light, pname, params, params_offset);
	}

	public void glGetLightiv(int light, int pname, IntBuffer params) {
		delegate.glGetLightiv(light, pname, params);
	}

	public void glGetListParameterfvSGIX(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetListParameterfvSGIX(target, pname, params, params_offset);
	}

	public void glGetListParameterfvSGIX(int target, int pname,
			FloatBuffer params) {
		delegate.glGetListParameterfvSGIX(target, pname, params);
	}

	public void glGetListParameterivSGIX(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetListParameterivSGIX(target, pname, params, params_offset);
	}

	public void glGetListParameterivSGIX(int target, int pname, IntBuffer params) {
		delegate.glGetListParameterivSGIX(target, pname, params);
	}

	public void glGetLocalConstantBooleanvEXT(int id, int pname,
			byte[] program, int program_offset) {
		delegate.glGetLocalConstantBooleanvEXT(id, pname, program,
				program_offset);
	}

	public void glGetLocalConstantBooleanvEXT(int id, int pname,
			ByteBuffer program) {
		delegate.glGetLocalConstantBooleanvEXT(id, pname, program);
	}

	public void glGetLocalConstantFloatvEXT(int target, int pname,
			float[] params, int params_offset) {
		delegate.glGetLocalConstantFloatvEXT(target, pname, params,
				params_offset);
	}

	public void glGetLocalConstantFloatvEXT(int target, int pname,
			FloatBuffer params) {
		delegate.glGetLocalConstantFloatvEXT(target, pname, params);
	}

	public void glGetLocalConstantIntegervEXT(int target, int pname,
			int[] params, int params_offset) {
		delegate.glGetLocalConstantIntegervEXT(target, pname, params,
				params_offset);
	}

	public void glGetLocalConstantIntegervEXT(int target, int pname,
			IntBuffer params) {
		delegate.glGetLocalConstantIntegervEXT(target, pname, params);
	}

	public void glGetMapAttribParameterfvNV(int stage, int portion, int pname,
			float[] params, int params_offset) {
		delegate.glGetMapAttribParameterfvNV(stage, portion, pname, params,
				params_offset);
	}

	public void glGetMapAttribParameterfvNV(int stage, int portion, int pname,
			FloatBuffer params) {
		delegate.glGetMapAttribParameterfvNV(stage, portion, pname, params);
	}

	public void glGetMapAttribParameterivNV(int stage, int portion, int pname,
			int[] params, int params_offset) {
		delegate.glGetMapAttribParameterivNV(stage, portion, pname, params,
				params_offset);
	}

	public void glGetMapAttribParameterivNV(int stage, int portion, int pname,
			IntBuffer params) {
		delegate.glGetMapAttribParameterivNV(stage, portion, pname, params);
	}

	public void glGetMapControlPointsNV(int target, int index, int type,
			int ustride, int vstride, boolean packed, Buffer points) {
		delegate.glGetMapControlPointsNV(target, index, type, ustride, vstride,
				packed, points);
	}

	public void glGetMapdv(int target, int query, double[] v, int v_offset) {
		delegate.glGetMapdv(target, query, v, v_offset);
	}

	public void glGetMapdv(int target, int query, DoubleBuffer v) {
		delegate.glGetMapdv(target, query, v);
	}

	public void glGetMapfv(int target, int query, float[] v, int v_offset) {
		delegate.glGetMapfv(target, query, v, v_offset);
	}

	public void glGetMapfv(int target, int query, FloatBuffer v) {
		delegate.glGetMapfv(target, query, v);
	}

	public void glGetMapiv(int target, int query, int[] v, int v_offset) {
		delegate.glGetMapiv(target, query, v, v_offset);
	}

	public void glGetMapiv(int target, int query, IntBuffer v) {
		delegate.glGetMapiv(target, query, v);
	}

	public void glGetMapParameterfvNV(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetMapParameterfvNV(target, pname, params, params_offset);
	}

	public void glGetMapParameterfvNV(int target, int pname, FloatBuffer params) {
		delegate.glGetMapParameterfvNV(target, pname, params);
	}

	public void glGetMapParameterivNV(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetMapParameterivNV(target, pname, params, params_offset);
	}

	public void glGetMapParameterivNV(int target, int pname, IntBuffer params) {
		delegate.glGetMapParameterivNV(target, pname, params);
	}

	public void glGetMaterialfv(int face, int pname, float[] params,
			int params_offset) {
		delegate.glGetMaterialfv(face, pname, params, params_offset);
	}

	public void glGetMaterialfv(int face, int pname, FloatBuffer params) {
		delegate.glGetMaterialfv(face, pname, params);
	}

	public void glGetMaterialiv(int face, int pname, int[] params,
			int params_offset) {
		delegate.glGetMaterialiv(face, pname, params, params_offset);
	}

	public void glGetMaterialiv(int face, int pname, IntBuffer params) {
		delegate.glGetMaterialiv(face, pname, params);
	}

	public void glGetMinmax(int target, boolean reset, int format, int type,
			Buffer values) {
		delegate.glGetMinmax(target, reset, format, type, values);
	}

	public void glGetMinmax(int target, boolean reset, int format, int type,
			long values_buffer_offset) {
		delegate.glGetMinmax(target, reset, format, type, values_buffer_offset);
	}

	public void glGetMinmaxParameterfv(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetMinmaxParameterfv(target, pname, params, params_offset);
	}

	public void glGetMinmaxParameterfv(int target, int pname, FloatBuffer params) {
		delegate.glGetMinmaxParameterfv(target, pname, params);
	}

	public void glGetMinmaxParameteriv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetMinmaxParameteriv(target, pname, params, params_offset);
	}

	public void glGetMinmaxParameteriv(int target, int pname, IntBuffer params) {
		delegate.glGetMinmaxParameteriv(target, pname, params);
	}

	public void glGetObjectBufferfvATI(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetObjectBufferfvATI(target, pname, params, params_offset);
	}

	public void glGetObjectBufferfvATI(int target, int pname, FloatBuffer params) {
		delegate.glGetObjectBufferfvATI(target, pname, params);
	}

	public void glGetObjectBufferivATI(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetObjectBufferivATI(target, pname, params, params_offset);
	}

	public void glGetObjectBufferivATI(int target, int pname, IntBuffer params) {
		delegate.glGetObjectBufferivATI(target, pname, params);
	}

	public void glGetObjectParameterfvARB(int target, int pname,
			float[] params, int params_offset) {
		delegate
				.glGetObjectParameterfvARB(target, pname, params, params_offset);
	}

	public void glGetObjectParameterfvARB(int target, int pname,
			FloatBuffer params) {
		delegate.glGetObjectParameterfvARB(target, pname, params);
	}

	public void glGetObjectParameterivARB(int target, int pname, int[] params,
			int params_offset) {
		delegate
				.glGetObjectParameterivARB(target, pname, params, params_offset);
	}

	public void glGetObjectParameterivARB(int target, int pname,
			IntBuffer params) {
		delegate.glGetObjectParameterivARB(target, pname, params);
	}

	public void glGetOcclusionQueryivNV(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetOcclusionQueryivNV(target, pname, params, params_offset);
	}

	public void glGetOcclusionQueryivNV(int target, int pname, IntBuffer params) {
		delegate.glGetOcclusionQueryivNV(target, pname, params);
	}

	public void glGetOcclusionQueryuivNV(int id, int pname, int[] params,
			int params_offset) {
		delegate.glGetOcclusionQueryuivNV(id, pname, params, params_offset);
	}

	public void glGetOcclusionQueryuivNV(int id, int pname, IntBuffer params) {
		delegate.glGetOcclusionQueryuivNV(id, pname, params);
	}

	public void glGetPixelMapfv(int map, float[] values, int values_offset) {
		delegate.glGetPixelMapfv(map, values, values_offset);
	}

	public void glGetPixelMapfv(int map, FloatBuffer values) {
		delegate.glGetPixelMapfv(map, values);
	}

	public void glGetPixelMapfv(int map, long values_buffer_offset) {
		delegate.glGetPixelMapfv(map, values_buffer_offset);
	}

	public void glGetPixelMapuiv(int map, int[] values, int values_offset) {
		delegate.glGetPixelMapuiv(map, values, values_offset);
	}

	public void glGetPixelMapuiv(int map, IntBuffer values) {
		delegate.glGetPixelMapuiv(map, values);
	}

	public void glGetPixelMapuiv(int map, long values_buffer_offset) {
		delegate.glGetPixelMapuiv(map, values_buffer_offset);
	}

	public void glGetPixelMapusv(int map, long values_buffer_offset) {
		delegate.glGetPixelMapusv(map, values_buffer_offset);
	}

	public void glGetPixelMapusv(int map, short[] values, int values_offset) {
		delegate.glGetPixelMapusv(map, values, values_offset);
	}

	public void glGetPixelMapusv(int map, ShortBuffer values) {
		delegate.glGetPixelMapusv(map, values);
	}

	public void glGetPixelTexGenParameterfvSGIS(int pname, float[] params,
			int params_offset) {
		delegate.glGetPixelTexGenParameterfvSGIS(pname, params, params_offset);
	}

	public void glGetPixelTexGenParameterfvSGIS(int pname, FloatBuffer params) {
		delegate.glGetPixelTexGenParameterfvSGIS(pname, params);
	}

	public void glGetPixelTexGenParameterivSGIS(int pname, int[] params,
			int params_offset) {
		delegate.glGetPixelTexGenParameterivSGIS(pname, params, params_offset);
	}

	public void glGetPixelTexGenParameterivSGIS(int pname, IntBuffer params) {
		delegate.glGetPixelTexGenParameterivSGIS(pname, params);
	}

	public void glGetPolygonStipple(byte[] mask, int mask_offset) {
		delegate.glGetPolygonStipple(mask, mask_offset);
	}

	public void glGetPolygonStipple(ByteBuffer mask) {
		delegate.glGetPolygonStipple(mask);
	}

	public void glGetPolygonStipple(long mask_buffer_offset) {
		delegate.glGetPolygonStipple(mask_buffer_offset);
	}

	public void glGetProgramEnvParameterdvARB(int index, int pname,
			double[] params, int params_offset) {
		delegate.glGetProgramEnvParameterdvARB(index, pname, params,
				params_offset);
	}

	public void glGetProgramEnvParameterdvARB(int index, int pname,
			DoubleBuffer params) {
		delegate.glGetProgramEnvParameterdvARB(index, pname, params);
	}

	public void glGetProgramEnvParameterfvARB(int target, int pname,
			float[] params, int params_offset) {
		delegate.glGetProgramEnvParameterfvARB(target, pname, params,
				params_offset);
	}

	public void glGetProgramEnvParameterfvARB(int target, int pname,
			FloatBuffer params) {
		delegate.glGetProgramEnvParameterfvARB(target, pname, params);
	}

	public void glGetProgramEnvParameterIivNV(int target, int index,
			int[] params, int params_offset) {
		delegate.glGetProgramEnvParameterIivNV(target, index, params,
				params_offset);
	}

	public void glGetProgramEnvParameterIivNV(int target, int index,
			IntBuffer params) {
		delegate.glGetProgramEnvParameterIivNV(target, index, params);
	}

	public void glGetProgramEnvParameterIuivNV(int target, int index,
			int[] params, int params_offset) {
		delegate.glGetProgramEnvParameterIuivNV(target, index, params,
				params_offset);
	}

	public void glGetProgramEnvParameterIuivNV(int target, int index,
			IntBuffer params) {
		delegate.glGetProgramEnvParameterIuivNV(target, index, params);
	}

	public void glGetProgramInfoLog(int program, int bufSize, int[] length,
			int length_offset, byte[] infoLog, int infoLog_offset) {
		delegate.glGetProgramInfoLog(program, bufSize, length, length_offset,
				infoLog, infoLog_offset);
	}

	public void glGetProgramInfoLog(int program, int bufSize, IntBuffer length,
			ByteBuffer infoLog) {
		delegate.glGetProgramInfoLog(program, bufSize, length, infoLog);
	}

	public void glGetProgramiv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetProgramiv(target, pname, params, params_offset);
	}

	public void glGetProgramiv(int target, int pname, IntBuffer params) {
		delegate.glGetProgramiv(target, pname, params);
	}

	public void glGetProgramivARB(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetProgramivARB(target, pname, params, params_offset);
	}

	public void glGetProgramivARB(int target, int pname, IntBuffer params) {
		delegate.glGetProgramivARB(target, pname, params);
	}

	public void glGetProgramivNV(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetProgramivNV(target, pname, params, params_offset);
	}

	public void glGetProgramivNV(int target, int pname, IntBuffer params) {
		delegate.glGetProgramivNV(target, pname, params);
	}

	public void glGetProgramLocalParameterdvARB(int index, int pname,
			double[] params, int params_offset) {
		delegate.glGetProgramLocalParameterdvARB(index, pname, params,
				params_offset);
	}

	public void glGetProgramLocalParameterdvARB(int index, int pname,
			DoubleBuffer params) {
		delegate.glGetProgramLocalParameterdvARB(index, pname, params);
	}

	public void glGetProgramLocalParameterfvARB(int target, int pname,
			float[] params, int params_offset) {
		delegate.glGetProgramLocalParameterfvARB(target, pname, params,
				params_offset);
	}

	public void glGetProgramLocalParameterfvARB(int target, int pname,
			FloatBuffer params) {
		delegate.glGetProgramLocalParameterfvARB(target, pname, params);
	}

	public void glGetProgramLocalParameterIivNV(int target, int index,
			int[] params, int params_offset) {
		delegate.glGetProgramLocalParameterIivNV(target, index, params,
				params_offset);
	}

	public void glGetProgramLocalParameterIivNV(int target, int index,
			IntBuffer params) {
		delegate.glGetProgramLocalParameterIivNV(target, index, params);
	}

	public void glGetProgramLocalParameterIuivNV(int target, int index,
			int[] params, int params_offset) {
		delegate.glGetProgramLocalParameterIuivNV(target, index, params,
				params_offset);
	}

	public void glGetProgramLocalParameterIuivNV(int target, int index,
			IntBuffer params) {
		delegate.glGetProgramLocalParameterIuivNV(target, index, params);
	}

	public void glGetProgramNamedParameterdvNV(int id, int len, String name,
			double[] params, int params_offset) {
		delegate.glGetProgramNamedParameterdvNV(id, len, name, params,
				params_offset);
	}

	public void glGetProgramNamedParameterdvNV(int id, int len, String name,
			DoubleBuffer params) {
		delegate.glGetProgramNamedParameterdvNV(id, len, name, params);
	}

	public void glGetProgramNamedParameterfvNV(int id, int len, String name,
			float[] params, int params_offset) {
		delegate.glGetProgramNamedParameterfvNV(id, len, name, params,
				params_offset);
	}

	public void glGetProgramNamedParameterfvNV(int id, int len, String name,
			FloatBuffer params) {
		delegate.glGetProgramNamedParameterfvNV(id, len, name, params);
	}

	public void glGetProgramParameterdvNV(int target, int index, int pname,
			double[] params, int params_offset) {
		delegate.glGetProgramParameterdvNV(target, index, pname, params,
				params_offset);
	}

	public void glGetProgramParameterdvNV(int target, int index, int pname,
			DoubleBuffer params) {
		delegate.glGetProgramParameterdvNV(target, index, pname, params);
	}

	public void glGetProgramParameterfvNV(int stage, int portion, int pname,
			float[] params, int params_offset) {
		delegate.glGetProgramParameterfvNV(stage, portion, pname, params,
				params_offset);
	}

	public void glGetProgramParameterfvNV(int stage, int portion, int pname,
			FloatBuffer params) {
		delegate.glGetProgramParameterfvNV(stage, portion, pname, params);
	}

	public void glGetProgramStringARB(int target, int pname, Buffer string) {
		delegate.glGetProgramStringARB(target, pname, string);
	}

	public void glGetProgramStringNV(int id, int pname, byte[] program,
			int program_offset) {
		delegate.glGetProgramStringNV(id, pname, program, program_offset);
	}

	public void glGetProgramStringNV(int id, int pname, ByteBuffer program) {
		delegate.glGetProgramStringNV(id, pname, program);
	}

	public void glGetQueryiv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetQueryiv(target, pname, params, params_offset);
	}

	public void glGetQueryiv(int target, int pname, IntBuffer params) {
		delegate.glGetQueryiv(target, pname, params);
	}

	public void glGetQueryivARB(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetQueryivARB(target, pname, params, params_offset);
	}

	public void glGetQueryivARB(int target, int pname, IntBuffer params) {
		delegate.glGetQueryivARB(target, pname, params);
	}

	public void glGetQueryObjecti64vEXT(int id, int pname, long[] params,
			int params_offset) {
		delegate.glGetQueryObjecti64vEXT(id, pname, params, params_offset);
	}

	public void glGetQueryObjecti64vEXT(int id, int pname, LongBuffer params) {
		delegate.glGetQueryObjecti64vEXT(id, pname, params);
	}

	public void glGetQueryObjectiv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetQueryObjectiv(target, pname, params, params_offset);
	}

	public void glGetQueryObjectiv(int target, int pname, IntBuffer params) {
		delegate.glGetQueryObjectiv(target, pname, params);
	}

	public void glGetQueryObjectivARB(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetQueryObjectivARB(target, pname, params, params_offset);
	}

	public void glGetQueryObjectivARB(int target, int pname, IntBuffer params) {
		delegate.glGetQueryObjectivARB(target, pname, params);
	}

	public void glGetQueryObjectui64vEXT(int id, int pname, long[] params,
			int params_offset) {
		delegate.glGetQueryObjectui64vEXT(id, pname, params, params_offset);
	}

	public void glGetQueryObjectui64vEXT(int id, int pname, LongBuffer params) {
		delegate.glGetQueryObjectui64vEXT(id, pname, params);
	}

	public void glGetQueryObjectuiv(int id, int pname, int[] params,
			int params_offset) {
		delegate.glGetQueryObjectuiv(id, pname, params, params_offset);
	}

	public void glGetQueryObjectuiv(int id, int pname, IntBuffer params) {
		delegate.glGetQueryObjectuiv(id, pname, params);
	}

	public void glGetQueryObjectuivARB(int id, int pname, int[] params,
			int params_offset) {
		delegate.glGetQueryObjectuivARB(id, pname, params, params_offset);
	}

	public void glGetQueryObjectuivARB(int id, int pname, IntBuffer params) {
		delegate.glGetQueryObjectuivARB(id, pname, params);
	}

	public void glGetRenderbufferParameterivEXT(int target, int pname,
			int[] params, int params_offset) {
		delegate.glGetRenderbufferParameterivEXT(target, pname, params,
				params_offset);
	}

	public void glGetRenderbufferParameterivEXT(int target, int pname,
			IntBuffer params) {
		delegate.glGetRenderbufferParameterivEXT(target, pname, params);
	}

	public void glGetSeparableFilter(int target, int format, int type,
			Buffer row, Buffer column, Buffer span) {
		delegate.glGetSeparableFilter(target, format, type, row, column, span);
	}

	public void glGetSeparableFilter(int target, int format, int type,
			long row_buffer_offset, long column_buffer_offset,
			long span_buffer_offset) {
		delegate.glGetSeparableFilter(target, format, type, row_buffer_offset,
				column_buffer_offset, span_buffer_offset);
	}

	public void glGetShaderInfoLog(int program, int bufSize, int[] length,
			int length_offset, byte[] infoLog, int infoLog_offset) {
		delegate.glGetShaderInfoLog(program, bufSize, length, length_offset,
				infoLog, infoLog_offset);
	}

	public void glGetShaderInfoLog(int program, int bufSize, IntBuffer length,
			ByteBuffer infoLog) {
		delegate.glGetShaderInfoLog(program, bufSize, length, infoLog);
	}

	public void glGetShaderiv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetShaderiv(target, pname, params, params_offset);
	}

	public void glGetShaderiv(int target, int pname, IntBuffer params) {
		delegate.glGetShaderiv(target, pname, params);
	}

	public void glGetShaderSource(int program, int bufSize, int[] length,
			int length_offset, byte[] infoLog, int infoLog_offset) {
		delegate.glGetShaderSource(program, bufSize, length, length_offset,
				infoLog, infoLog_offset);
	}

	public void glGetShaderSource(int program, int bufSize, IntBuffer length,
			ByteBuffer infoLog) {
		delegate.glGetShaderSource(program, bufSize, length, infoLog);
	}

	public void glGetShaderSourceARB(int program, int bufSize, int[] length,
			int length_offset, byte[] infoLog, int infoLog_offset) {
		delegate.glGetShaderSourceARB(program, bufSize, length, length_offset,
				infoLog, infoLog_offset);
	}

	public void glGetShaderSourceARB(int program, int bufSize,
			IntBuffer length, ByteBuffer infoLog) {
		delegate.glGetShaderSourceARB(program, bufSize, length, infoLog);
	}

	public void glGetSharpenTexFuncSGIS(int pname, float[] params,
			int params_offset) {
		delegate.glGetSharpenTexFuncSGIS(pname, params, params_offset);
	}

	public void glGetSharpenTexFuncSGIS(int pname, FloatBuffer params) {
		delegate.glGetSharpenTexFuncSGIS(pname, params);
	}

	public String glGetString(int name) {
		return delegate.glGetString(name);
	}

	public void glGetTexBumpParameterfvATI(int pname, float[] params,
			int params_offset) {
		delegate.glGetTexBumpParameterfvATI(pname, params, params_offset);
	}

	public void glGetTexBumpParameterfvATI(int pname, FloatBuffer params) {
		delegate.glGetTexBumpParameterfvATI(pname, params);
	}

	public void glGetTexBumpParameterivATI(int pname, int[] params,
			int params_offset) {
		delegate.glGetTexBumpParameterivATI(pname, params, params_offset);
	}

	public void glGetTexBumpParameterivATI(int pname, IntBuffer params) {
		delegate.glGetTexBumpParameterivATI(pname, params);
	}

	public void glGetTexEnvfv(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetTexEnvfv(target, pname, params, params_offset);
	}

	public void glGetTexEnvfv(int target, int pname, FloatBuffer params) {
		delegate.glGetTexEnvfv(target, pname, params);
	}

	public void glGetTexEnviv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetTexEnviv(target, pname, params, params_offset);
	}

	public void glGetTexEnviv(int target, int pname, IntBuffer params) {
		delegate.glGetTexEnviv(target, pname, params);
	}

	public void glGetTexFilterFuncSGIS(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetTexFilterFuncSGIS(target, pname, params, params_offset);
	}

	public void glGetTexFilterFuncSGIS(int target, int pname, FloatBuffer params) {
		delegate.glGetTexFilterFuncSGIS(target, pname, params);
	}

	public void glGetTexGendv(int coord, int pname, double[] params,
			int params_offset) {
		delegate.glGetTexGendv(coord, pname, params, params_offset);
	}

	public void glGetTexGendv(int coord, int pname, DoubleBuffer params) {
		delegate.glGetTexGendv(coord, pname, params);
	}

	public void glGetTexGenfv(int coord, int pname, float[] params,
			int params_offset) {
		delegate.glGetTexGenfv(coord, pname, params, params_offset);
	}

	public void glGetTexGenfv(int coord, int pname, FloatBuffer params) {
		delegate.glGetTexGenfv(coord, pname, params);
	}

	public void glGetTexGeniv(int coord, int pname, int[] params,
			int params_offset) {
		delegate.glGetTexGeniv(coord, pname, params, params_offset);
	}

	public void glGetTexGeniv(int coord, int pname, IntBuffer params) {
		delegate.glGetTexGeniv(coord, pname, params);
	}

	public void glGetTexImage(int target, int level, int format, int type,
			Buffer pixels) {
		delegate.glGetTexImage(target, level, format, type, pixels);
	}

	public void glGetTexImage(int target, int level, int format, int type,
			long pixels_buffer_offset) {
		delegate.glGetTexImage(target, level, format, type,
				pixels_buffer_offset);
	}

	public void glGetTexLevelParameterfv(int target, int level, int pname,
			float[] params, int params_offset) {
		delegate.glGetTexLevelParameterfv(target, level, pname, params,
				params_offset);
	}

	public void glGetTexLevelParameterfv(int target, int level, int pname,
			FloatBuffer params) {
		delegate.glGetTexLevelParameterfv(target, level, pname, params);
	}

	public void glGetTexLevelParameteriv(int target, int level, int pname,
			int[] params, int params_offset) {
		delegate.glGetTexLevelParameteriv(target, level, pname, params,
				params_offset);
	}

	public void glGetTexLevelParameteriv(int target, int level, int pname,
			IntBuffer params) {
		delegate.glGetTexLevelParameteriv(target, level, pname, params);
	}

	public void glGetTexParameterfv(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetTexParameterfv(target, pname, params, params_offset);
	}

	public void glGetTexParameterfv(int target, int pname, FloatBuffer params) {
		delegate.glGetTexParameterfv(target, pname, params);
	}

	public void glGetTexParameterIivEXT(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetTexParameterIivEXT(target, pname, params, params_offset);
	}

	public void glGetTexParameterIivEXT(int target, int pname, IntBuffer params) {
		delegate.glGetTexParameterIivEXT(target, pname, params);
	}

	public void glGetTexParameterIuivEXT(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetTexParameterIuivEXT(target, pname, params, params_offset);
	}

	public void glGetTexParameterIuivEXT(int target, int pname, IntBuffer params) {
		delegate.glGetTexParameterIuivEXT(target, pname, params);
	}

	public void glGetTexParameteriv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetTexParameteriv(target, pname, params, params_offset);
	}

	public void glGetTexParameteriv(int target, int pname, IntBuffer params) {
		delegate.glGetTexParameteriv(target, pname, params);
	}

	public void glGetTrackMatrixivNV(int stage, int portion, int pname,
			int[] params, int params_offset) {
		delegate.glGetTrackMatrixivNV(stage, portion, pname, params,
				params_offset);
	}

	public void glGetTrackMatrixivNV(int stage, int portion, int pname,
			IntBuffer params) {
		delegate.glGetTrackMatrixivNV(stage, portion, pname, params);
	}

	public void glGetTransformFeedbackVaryingNV(int program, int index,
			int[] location, int location_offset) {
		delegate.glGetTransformFeedbackVaryingNV(program, index, location,
				location_offset);
	}

	public void glGetTransformFeedbackVaryingNV(int program, int index,
			IntBuffer location) {
		delegate.glGetTransformFeedbackVaryingNV(program, index, location);
	}

	public int glGetUniformBufferSizeEXT(int program, int location) {
		return delegate.glGetUniformBufferSizeEXT(program, location);
	}

	public void glGetUniformfv(int program, int location, float[] params,
			int params_offset) {
		delegate.glGetUniformfv(program, location, params, params_offset);
	}

	public void glGetUniformfv(int program, int location, FloatBuffer params) {
		delegate.glGetUniformfv(program, location, params);
	}

	public void glGetUniformfvARB(int program, int location, float[] params,
			int params_offset) {
		delegate.glGetUniformfvARB(program, location, params, params_offset);
	}

	public void glGetUniformfvARB(int program, int location, FloatBuffer params) {
		delegate.glGetUniformfvARB(program, location, params);
	}

	public void glGetUniformiv(int program, int location, int[] params,
			int params_offset) {
		delegate.glGetUniformiv(program, location, params, params_offset);
	}

	public void glGetUniformiv(int program, int location, IntBuffer params) {
		delegate.glGetUniformiv(program, location, params);
	}

	public void glGetUniformivARB(int program, int location, int[] params,
			int params_offset) {
		delegate.glGetUniformivARB(program, location, params, params_offset);
	}

	public void glGetUniformivARB(int program, int location, IntBuffer params) {
		delegate.glGetUniformivARB(program, location, params);
	}

	public int glGetUniformLocation(int program, String name) {
		return delegate.glGetUniformLocation(program, name);
	}

	public int glGetUniformLocationARB(int program, String name) {
		return delegate.glGetUniformLocationARB(program, name);
	}

	public int glGetUniformOffsetEXT(int program, int location) {
		return delegate.glGetUniformOffsetEXT(program, location);
	}

	public void glGetUniformuivEXT(int program, int location, int[] params,
			int params_offset) {
		delegate.glGetUniformuivEXT(program, location, params, params_offset);
	}

	public void glGetUniformuivEXT(int program, int location, IntBuffer params) {
		delegate.glGetUniformuivEXT(program, location, params);
	}

	public void glGetVariantArrayObjectfvATI(int target, int pname,
			float[] params, int params_offset) {
		delegate.glGetVariantArrayObjectfvATI(target, pname, params,
				params_offset);
	}

	public void glGetVariantArrayObjectfvATI(int target, int pname,
			FloatBuffer params) {
		delegate.glGetVariantArrayObjectfvATI(target, pname, params);
	}

	public void glGetVariantArrayObjectivATI(int target, int pname,
			int[] params, int params_offset) {
		delegate.glGetVariantArrayObjectivATI(target, pname, params,
				params_offset);
	}

	public void glGetVariantArrayObjectivATI(int target, int pname,
			IntBuffer params) {
		delegate.glGetVariantArrayObjectivATI(target, pname, params);
	}

	public void glGetVariantBooleanvEXT(int id, int pname, byte[] program,
			int program_offset) {
		delegate.glGetVariantBooleanvEXT(id, pname, program, program_offset);
	}

	public void glGetVariantBooleanvEXT(int id, int pname, ByteBuffer program) {
		delegate.glGetVariantBooleanvEXT(id, pname, program);
	}

	public void glGetVariantFloatvEXT(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetVariantFloatvEXT(target, pname, params, params_offset);
	}

	public void glGetVariantFloatvEXT(int target, int pname, FloatBuffer params) {
		delegate.glGetVariantFloatvEXT(target, pname, params);
	}

	public void glGetVariantIntegervEXT(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetVariantIntegervEXT(target, pname, params, params_offset);
	}

	public void glGetVariantIntegervEXT(int target, int pname, IntBuffer params) {
		delegate.glGetVariantIntegervEXT(target, pname, params);
	}

	public int glGetVaryingLocationNV(int program, byte[] name, int name_offset) {
		return delegate.glGetVaryingLocationNV(program, name, name_offset);
	}

	public int glGetVaryingLocationNV(int program, ByteBuffer name) {
		return delegate.glGetVaryingLocationNV(program, name);
	}

	public void glGetVertexAttribArrayObjectfvATI(int target, int pname,
			float[] params, int params_offset) {
		delegate.glGetVertexAttribArrayObjectfvATI(target, pname, params,
				params_offset);
	}

	public void glGetVertexAttribArrayObjectfvATI(int target, int pname,
			FloatBuffer params) {
		delegate.glGetVertexAttribArrayObjectfvATI(target, pname, params);
	}

	public void glGetVertexAttribArrayObjectivATI(int target, int pname,
			int[] params, int params_offset) {
		delegate.glGetVertexAttribArrayObjectivATI(target, pname, params,
				params_offset);
	}

	public void glGetVertexAttribArrayObjectivATI(int target, int pname,
			IntBuffer params) {
		delegate.glGetVertexAttribArrayObjectivATI(target, pname, params);
	}

	public void glGetVertexAttribdv(int index, int pname, double[] params,
			int params_offset) {
		delegate.glGetVertexAttribdv(index, pname, params, params_offset);
	}

	public void glGetVertexAttribdv(int index, int pname, DoubleBuffer params) {
		delegate.glGetVertexAttribdv(index, pname, params);
	}

	public void glGetVertexAttribdvARB(int index, int pname, double[] params,
			int params_offset) {
		delegate.glGetVertexAttribdvARB(index, pname, params, params_offset);
	}

	public void glGetVertexAttribdvARB(int index, int pname, DoubleBuffer params) {
		delegate.glGetVertexAttribdvARB(index, pname, params);
	}

	public void glGetVertexAttribdvNV(int index, int pname, double[] params,
			int params_offset) {
		delegate.glGetVertexAttribdvNV(index, pname, params, params_offset);
	}

	public void glGetVertexAttribdvNV(int index, int pname, DoubleBuffer params) {
		delegate.glGetVertexAttribdvNV(index, pname, params);
	}

	public void glGetVertexAttribfv(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetVertexAttribfv(target, pname, params, params_offset);
	}

	public void glGetVertexAttribfv(int target, int pname, FloatBuffer params) {
		delegate.glGetVertexAttribfv(target, pname, params);
	}

	public void glGetVertexAttribfvARB(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetVertexAttribfvARB(target, pname, params, params_offset);
	}

	public void glGetVertexAttribfvARB(int target, int pname, FloatBuffer params) {
		delegate.glGetVertexAttribfvARB(target, pname, params);
	}

	public void glGetVertexAttribfvNV(int target, int pname, float[] params,
			int params_offset) {
		delegate.glGetVertexAttribfvNV(target, pname, params, params_offset);
	}

	public void glGetVertexAttribfvNV(int target, int pname, FloatBuffer params) {
		delegate.glGetVertexAttribfvNV(target, pname, params);
	}

	public void glGetVertexAttribIivEXT(int index, int pname, int[] params,
			int params_offset) {
		delegate.glGetVertexAttribIivEXT(index, pname, params, params_offset);
	}

	public void glGetVertexAttribIivEXT(int index, int pname, IntBuffer params) {
		delegate.glGetVertexAttribIivEXT(index, pname, params);
	}

	public void glGetVertexAttribIuivEXT(int index, int pname, int[] params,
			int params_offset) {
		delegate.glGetVertexAttribIuivEXT(index, pname, params, params_offset);
	}

	public void glGetVertexAttribIuivEXT(int index, int pname, IntBuffer params) {
		delegate.glGetVertexAttribIuivEXT(index, pname, params);
	}

	public void glGetVertexAttribiv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetVertexAttribiv(target, pname, params, params_offset);
	}

	public void glGetVertexAttribiv(int target, int pname, IntBuffer params) {
		delegate.glGetVertexAttribiv(target, pname, params);
	}

	public void glGetVertexAttribivARB(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetVertexAttribivARB(target, pname, params, params_offset);
	}

	public void glGetVertexAttribivARB(int target, int pname, IntBuffer params) {
		delegate.glGetVertexAttribivARB(target, pname, params);
	}

	public void glGetVertexAttribivNV(int target, int pname, int[] params,
			int params_offset) {
		delegate.glGetVertexAttribivNV(target, pname, params, params_offset);
	}

	public void glGetVertexAttribivNV(int target, int pname, IntBuffer params) {
		delegate.glGetVertexAttribivNV(target, pname, params);
	}

	public void glGlobalAlphaFactorbSUN(byte factor) {
		delegate.glGlobalAlphaFactorbSUN(factor);
	}

	public void glGlobalAlphaFactordSUN(double coord) {
		delegate.glGlobalAlphaFactordSUN(coord);
	}

	public void glGlobalAlphaFactorfSUN(float coord) {
		delegate.glGlobalAlphaFactorfSUN(coord);
	}

	public void glGlobalAlphaFactoriSUN(int count) {
		delegate.glGlobalAlphaFactoriSUN(count);
	}

	public void glGlobalAlphaFactorsSUN(short factor) {
		delegate.glGlobalAlphaFactorsSUN(factor);
	}

	public void glGlobalAlphaFactorubSUN(byte factor) {
		delegate.glGlobalAlphaFactorubSUN(factor);
	}

	public void glGlobalAlphaFactoruiSUN(int mode) {
		delegate.glGlobalAlphaFactoruiSUN(mode);
	}

	public void glGlobalAlphaFactorusSUN(short factor) {
		delegate.glGlobalAlphaFactorusSUN(factor);
	}

	public void glHint(int target, int mode) {
		delegate.glHint(target, mode);
	}

	public void glHintPGI(int target, int s) {
		delegate.glHintPGI(target, s);
	}

	public void glHistogram(int target, int width, int internalformat,
			boolean sink) {
		delegate.glHistogram(target, width, internalformat, sink);
	}

	public void glIglooInterfaceSGIX(int pname, Buffer params) {
		delegate.glIglooInterfaceSGIX(pname, params);
	}

	public void glImageTransformParameterfHP(int target, int pname, float params) {
		delegate.glImageTransformParameterfHP(target, pname, params);
	}

	public void glImageTransformParameterfvHP(int target, int pname,
			float[] params, int params_offset) {
		delegate.glImageTransformParameterfvHP(target, pname, params,
				params_offset);
	}

	public void glImageTransformParameterfvHP(int target, int pname,
			FloatBuffer params) {
		delegate.glImageTransformParameterfvHP(target, pname, params);
	}

	public void glImageTransformParameteriHP(int target, int pname, int params) {
		delegate.glImageTransformParameteriHP(target, pname, params);
	}

	public void glImageTransformParameterivHP(int target, int pname,
			int[] params, int params_offset) {
		delegate.glImageTransformParameterivHP(target, pname, params,
				params_offset);
	}

	public void glImageTransformParameterivHP(int target, int pname,
			IntBuffer params) {
		delegate.glImageTransformParameterivHP(target, pname, params);
	}

	public void glIndexd(double c) {
		delegate.glIndexd(c);
	}

	public void glIndexdv(double[] c, int c_offset) {
		delegate.glIndexdv(c, c_offset);
	}

	public void glIndexdv(DoubleBuffer c) {
		delegate.glIndexdv(c);
	}

	public void glIndexf(float c) {
		delegate.glIndexf(c);
	}

	public void glIndexFuncEXT(int target, float s) {
		delegate.glIndexFuncEXT(target, s);
	}

	public void glIndexfv(float[] c, int c_offset) {
		delegate.glIndexfv(c, c_offset);
	}

	public void glIndexfv(FloatBuffer c) {
		delegate.glIndexfv(c);
	}

	public void glIndexi(int c) {
		delegate.glIndexi(c);
	}

	public void glIndexiv(int[] c, int c_offset) {
		delegate.glIndexiv(c, c_offset);
	}

	public void glIndexiv(IntBuffer c) {
		delegate.glIndexiv(c);
	}

	public void glIndexMask(int mask) {
		delegate.glIndexMask(mask);
	}

	public void glIndexMaterialEXT(int target, int id) {
		delegate.glIndexMaterialEXT(target, id);
	}

	public void glIndexPointer(int type, int stride, Buffer ptr) {
		delegate.glIndexPointer(type, stride, ptr);
	}

	public void glIndexs(short c) {
		delegate.glIndexs(c);
	}

	public void glIndexsv(short[] c, int c_offset) {
		delegate.glIndexsv(c, c_offset);
	}

	public void glIndexsv(ShortBuffer c) {
		delegate.glIndexsv(c);
	}

	public void glIndexub(byte c) {
		delegate.glIndexub(c);
	}

	public void glIndexubv(byte[] c, int c_offset) {
		delegate.glIndexubv(c, c_offset);
	}

	public void glIndexubv(ByteBuffer c) {
		delegate.glIndexubv(c);
	}

	public void glInitNames() {
		delegate.glInitNames();
	}

	public void glInsertComponentEXT(int red, int green, int blue) {
		delegate.glInsertComponentEXT(red, green, blue);
	}

	public void glInstrumentsBufferSGIX(int size, int[] buffer,
			int buffer_offset) {
		delegate.glInstrumentsBufferSGIX(size, buffer, buffer_offset);
	}

	public void glInstrumentsBufferSGIX(int size, IntBuffer buffer) {
		delegate.glInstrumentsBufferSGIX(size, buffer);
	}

	public void glInterleavedArrays(int format, int stride, Buffer pointer) {
		delegate.glInterleavedArrays(format, stride, pointer);
	}

	public void glInterleavedArrays(int format, int stride,
			long pointer_buffer_offset) {
		delegate.glInterleavedArrays(format, stride, pointer_buffer_offset);
	}

	public boolean glIsAsyncMarkerSGIX(int id) {
		return delegate.glIsAsyncMarkerSGIX(id);
	}

	public boolean glIsBuffer(int id) {
		return delegate.glIsBuffer(id);
	}

	public boolean glIsBufferARB(int id) {
		return delegate.glIsBufferARB(id);
	}

	public boolean glIsEnabled(int cap) {
		return delegate.glIsEnabled(cap);
	}

	public boolean glIsEnabledIndexedEXT(int target, int index) {
		return delegate.glIsEnabledIndexedEXT(target, index);
	}

	public boolean glIsFenceAPPLE(int id) {
		return delegate.glIsFenceAPPLE(id);
	}

	public boolean glIsFenceNV(int id) {
		return delegate.glIsFenceNV(id);
	}

	public boolean glIsFramebufferEXT(int id) {
		return delegate.glIsFramebufferEXT(id);
	}

	public boolean glIsList(int list) {
		return delegate.glIsList(list);
	}

	public boolean glIsObjectBufferATI(int id) {
		return delegate.glIsObjectBufferATI(id);
	}

	public boolean glIsOcclusionQueryNV(int id) {
		return delegate.glIsOcclusionQueryNV(id);
	}

	public boolean glIsProgram(int id) {
		return delegate.glIsProgram(id);
	}

	public boolean glIsProgramARB(int id) {
		return delegate.glIsProgramARB(id);
	}

	public boolean glIsProgramNV(int id) {
		return delegate.glIsProgramNV(id);
	}

	public boolean glIsQuery(int id) {
		return delegate.glIsQuery(id);
	}

	public boolean glIsQueryARB(int id) {
		return delegate.glIsQueryARB(id);
	}

	public boolean glIsRenderbufferEXT(int id) {
		return delegate.glIsRenderbufferEXT(id);
	}

	public boolean glIsShader(int id) {
		return delegate.glIsShader(id);
	}

	public boolean glIsTexture(int texture) {
		return delegate.glIsTexture(texture);
	}

	public boolean glIsVariantEnabledEXT(int id, int cap) {
		return delegate.glIsVariantEnabledEXT(id, cap);
	}

	public boolean glIsVertexArrayAPPLE(int id) {
		return delegate.glIsVertexArrayAPPLE(id);
	}

	public boolean glIsVertexAttribEnabledAPPLE(int index, int pname) {
		return delegate.glIsVertexAttribEnabledAPPLE(index, pname);
	}

	public void glLightEnviSGIX(int target, int s) {
		delegate.glLightEnviSGIX(target, s);
	}

	public void glLightf(int light, int pname, float param) {
		delegate.glLightf(light, pname, param);
	}

	public void glLightfv(int light, int pname, float[] params,
			int params_offset) {
		delegate.glLightfv(light, pname, params, params_offset);
	}

	public void glLightfv(int light, int pname, FloatBuffer params) {
		delegate.glLightfv(light, pname, params);
	}

	public void glLighti(int light, int pname, int param) {
		delegate.glLighti(light, pname, param);
	}

	public void glLightiv(int light, int pname, int[] params, int params_offset) {
		delegate.glLightiv(light, pname, params, params_offset);
	}

	public void glLightiv(int light, int pname, IntBuffer params) {
		delegate.glLightiv(light, pname, params);
	}

	public void glLightModelf(int pname, float param) {
		delegate.glLightModelf(pname, param);
	}

	public void glLightModelfv(int pname, float[] params, int params_offset) {
		delegate.glLightModelfv(pname, params, params_offset);
	}

	public void glLightModelfv(int pname, FloatBuffer params) {
		delegate.glLightModelfv(pname, params);
	}

	public void glLightModeli(int pname, int param) {
		delegate.glLightModeli(pname, param);
	}

	public void glLightModeliv(int pname, int[] params, int params_offset) {
		delegate.glLightModeliv(pname, params, params_offset);
	}

	public void glLightModeliv(int pname, IntBuffer params) {
		delegate.glLightModeliv(pname, params);
	}

	public void glLineStipple(int factor, short pattern) {
		delegate.glLineStipple(factor, pattern);
	}

	public void glLineWidth(float width) {
		delegate.glLineWidth(width);
	}

	public void glLinkProgram(int mode) {
		delegate.glLinkProgram(mode);
	}

	public void glLinkProgramARB(int mode) {
		delegate.glLinkProgramARB(mode);
	}

	public void glListBase(int base) {
		delegate.glListBase(base);
	}

	public void glListParameterfSGIX(int target, int pname, float params) {
		delegate.glListParameterfSGIX(target, pname, params);
	}

	public void glListParameterfvSGIX(int target, int pname, float[] params,
			int params_offset) {
		delegate.glListParameterfvSGIX(target, pname, params, params_offset);
	}

	public void glListParameterfvSGIX(int target, int pname, FloatBuffer params) {
		delegate.glListParameterfvSGIX(target, pname, params);
	}

	public void glListParameteriSGIX(int target, int pname, int params) {
		delegate.glListParameteriSGIX(target, pname, params);
	}

	public void glListParameterivSGIX(int target, int pname, int[] params,
			int params_offset) {
		delegate.glListParameterivSGIX(target, pname, params, params_offset);
	}

	public void glListParameterivSGIX(int target, int pname, IntBuffer params) {
		delegate.glListParameterivSGIX(target, pname, params);
	}

	public void glLoadIdentity() {
		delegate.glLoadIdentity();
	}

	public void glLoadIdentityDeformationMapSGIX(int mode) {
		delegate.glLoadIdentityDeformationMapSGIX(mode);
	}

	public void glLoadMatrixd(double[] m, int m_offset) {
		delegate.glLoadMatrixd(m, m_offset);
	}

	public void glLoadMatrixd(DoubleBuffer m) {
		delegate.glLoadMatrixd(m);
	}

	public void glLoadMatrixf(float[] m, int m_offset) {
		delegate.glLoadMatrixf(m, m_offset);
	}

	public void glLoadMatrixf(FloatBuffer m) {
		delegate.glLoadMatrixf(m);
	}

	public void glLoadName(int name) {
		delegate.glLoadName(name);
	}

	public void glLoadProgramNV(int target, int id, int len, String program) {
		delegate.glLoadProgramNV(target, id, len, program);
	}

	public void glLoadTransposeMatrixd(double[] m, int m_offset) {
		delegate.glLoadTransposeMatrixd(m, m_offset);
	}

	public void glLoadTransposeMatrixd(DoubleBuffer m) {
		delegate.glLoadTransposeMatrixd(m);
	}

	public void glLoadTransposeMatrixf(float[] m, int m_offset) {
		delegate.glLoadTransposeMatrixf(m, m_offset);
	}

	public void glLoadTransposeMatrixf(FloatBuffer m) {
		delegate.glLoadTransposeMatrixf(m);
	}

	public void glLockArraysEXT(int x, int y) {
		delegate.glLockArraysEXT(x, y);
	}

	public void glLogicOp(int opcode) {
		delegate.glLogicOp(opcode);
	}

	public void glMap1d(int target, double u1, double u2, int stride,
			int order, double[] points, int points_offset) {
		delegate.glMap1d(target, u1, u2, stride, order, points, points_offset);
	}

	public void glMap1d(int target, double u1, double u2, int stride,
			int order, DoubleBuffer points) {
		delegate.glMap1d(target, u1, u2, stride, order, points);
	}

	public void glMap1f(int target, float u1, float u2, int stride, int order,
			float[] points, int points_offset) {
		delegate.glMap1f(target, u1, u2, stride, order, points, points_offset);
	}

	public void glMap1f(int target, float u1, float u2, int stride, int order,
			FloatBuffer points) {
		delegate.glMap1f(target, u1, u2, stride, order, points);
	}

	public void glMap2d(int target, double u1, double u2, int ustride,
			int uorder, double v1, double v2, int vstride, int vorder,
			double[] points, int points_offset) {
		delegate.glMap2d(target, u1, u2, ustride, uorder, v1, v2, vstride,
				vorder, points, points_offset);
	}

	public void glMap2d(int target, double u1, double u2, int ustride,
			int uorder, double v1, double v2, int vstride, int vorder,
			DoubleBuffer points) {
		delegate.glMap2d(target, u1, u2, ustride, uorder, v1, v2, vstride,
				vorder, points);
	}

	public void glMap2f(int target, float u1, float u2, int ustride,
			int uorder, float v1, float v2, int vstride, int vorder,
			float[] points, int points_offset) {
		delegate.glMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride,
				vorder, points, points_offset);
	}

	public void glMap2f(int target, float u1, float u2, int ustride,
			int uorder, float v1, float v2, int vstride, int vorder,
			FloatBuffer points) {
		delegate.glMap2f(target, u1, u2, ustride, uorder, v1, v2, vstride,
				vorder, points);
	}

	public ByteBuffer glMapBuffer(int target, int access) {
		return delegate.glMapBuffer(target, access);
	}

	public ByteBuffer glMapBufferARB(int target, int access) {
		return delegate.glMapBufferARB(target, access);
	}

	public void glMapControlPointsNV(int target, int index, int type,
			int ustride, int vstride, int uorder, int vorder, boolean packed,
			Buffer points) {
		delegate.glMapControlPointsNV(target, index, type, ustride, vstride,
				uorder, vorder, packed, points);
	}

	public void glMapGrid1d(int un, double u1, double u2) {
		delegate.glMapGrid1d(un, u1, u2);
	}

	public void glMapGrid1f(int un, float u1, float u2) {
		delegate.glMapGrid1f(un, u1, u2);
	}

	public void glMapGrid2d(int un, double u1, double u2, int vn, double v1,
			double v2) {
		delegate.glMapGrid2d(un, u1, u2, vn, v1, v2);
	}

	public void glMapGrid2f(int un, float u1, float u2, int vn, float v1,
			float v2) {
		delegate.glMapGrid2f(un, u1, u2, vn, v1, v2);
	}

	public void glMapParameterfvNV(int target, int pname, float[] params,
			int params_offset) {
		delegate.glMapParameterfvNV(target, pname, params, params_offset);
	}

	public void glMapParameterfvNV(int target, int pname, FloatBuffer params) {
		delegate.glMapParameterfvNV(target, pname, params);
	}

	public void glMapParameterivNV(int target, int pname, int[] params,
			int params_offset) {
		delegate.glMapParameterivNV(target, pname, params, params_offset);
	}

	public void glMapParameterivNV(int target, int pname, IntBuffer params) {
		delegate.glMapParameterivNV(target, pname, params);
	}

	public void glMapVertexAttrib1dAPPLE(int index, int size, double u1,
			double u2, int stride, int order, double[] points, int points_offset) {
		delegate.glMapVertexAttrib1dAPPLE(index, size, u1, u2, stride, order,
				points, points_offset);
	}

	public void glMapVertexAttrib1dAPPLE(int index, int size, double u1,
			double u2, int stride, int order, DoubleBuffer points) {
		delegate.glMapVertexAttrib1dAPPLE(index, size, u1, u2, stride, order,
				points);
	}

	public void glMapVertexAttrib1fAPPLE(int index, int size, float u1,
			float u2, int stride, int order, float[] points, int points_offset) {
		delegate.glMapVertexAttrib1fAPPLE(index, size, u1, u2, stride, order,
				points, points_offset);
	}

	public void glMapVertexAttrib1fAPPLE(int index, int size, float u1,
			float u2, int stride, int order, FloatBuffer points) {
		delegate.glMapVertexAttrib1fAPPLE(index, size, u1, u2, stride, order,
				points);
	}

	public void glMapVertexAttrib2dAPPLE(int index, int size, double u1,
			double u2, int ustride, int uorder, double v1, double v2,
			int vstride, int vorder, double[] points, int points_offset) {
		delegate.glMapVertexAttrib2dAPPLE(index, size, u1, u2, ustride, uorder,
				v1, v2, vstride, vorder, points, points_offset);
	}

	public void glMapVertexAttrib2dAPPLE(int index, int size, double u1,
			double u2, int ustride, int uorder, double v1, double v2,
			int vstride, int vorder, DoubleBuffer points) {
		delegate.glMapVertexAttrib2dAPPLE(index, size, u1, u2, ustride, uorder,
				v1, v2, vstride, vorder, points);
	}

	public void glMapVertexAttrib2fAPPLE(int index, int size, float u1,
			float u2, int ustride, int uorder, float v1, float v2, int vstride,
			int vorder, float[] points, int points_offset) {
		delegate.glMapVertexAttrib2fAPPLE(index, size, u1, u2, ustride, uorder,
				v1, v2, vstride, vorder, points, points_offset);
	}

	public void glMapVertexAttrib2fAPPLE(int index, int size, float u1,
			float u2, int ustride, int uorder, float v1, float v2, int vstride,
			int vorder, FloatBuffer points) {
		delegate.glMapVertexAttrib2fAPPLE(index, size, u1, u2, ustride, uorder,
				v1, v2, vstride, vorder, points);
	}

	public void glMaterialf(int face, int pname, float param) {
		delegate.glMaterialf(face, pname, param);
	}

	public void glMaterialfv(int face, int pname, float[] params,
			int params_offset) {
		delegate.glMaterialfv(face, pname, params, params_offset);
	}

	public void glMaterialfv(int face, int pname, FloatBuffer params) {
		delegate.glMaterialfv(face, pname, params);
	}

	public void glMateriali(int face, int pname, int param) {
		delegate.glMateriali(face, pname, param);
	}

	public void glMaterialiv(int face, int pname, int[] params,
			int params_offset) {
		delegate.glMaterialiv(face, pname, params, params_offset);
	}

	public void glMaterialiv(int face, int pname, IntBuffer params) {
		delegate.glMaterialiv(face, pname, params);
	}

	public void glMatrixIndexPointerARB(int size, int type, int stride,
			Buffer pointer) {
		delegate.glMatrixIndexPointerARB(size, type, stride, pointer);
	}

	public void glMatrixIndexPointerARB(int size, int type, int stride,
			long pointer_buffer_offset) {
		delegate.glMatrixIndexPointerARB(size, type, stride,
				pointer_buffer_offset);
	}

	public void glMatrixIndexubvARB(int size, byte[] weights, int weights_offset) {
		delegate.glMatrixIndexubvARB(size, weights, weights_offset);
	}

	public void glMatrixIndexubvARB(int size, ByteBuffer weights) {
		delegate.glMatrixIndexubvARB(size, weights);
	}

	public void glMatrixIndexuivARB(int n, int[] ids, int ids_offset) {
		delegate.glMatrixIndexuivARB(n, ids, ids_offset);
	}

	public void glMatrixIndexuivARB(int n, IntBuffer ids) {
		delegate.glMatrixIndexuivARB(n, ids);
	}

	public void glMatrixIndexusvARB(int size, short[] weights,
			int weights_offset) {
		delegate.glMatrixIndexusvARB(size, weights, weights_offset);
	}

	public void glMatrixIndexusvARB(int size, ShortBuffer weights) {
		delegate.glMatrixIndexusvARB(size, weights);
	}

	public void glMatrixMode(int mode) {
		delegate.glMatrixMode(mode);
	}

	public void glMinmax(int target, int internalformat, boolean sink) {
		delegate.glMinmax(target, internalformat, sink);
	}

	public void glMultiDrawArrays(int mode, int[] first, int first_offset,
			int[] count, int count_offset, int primcount) {
		delegate.glMultiDrawArrays(mode, first, first_offset, count,
				count_offset, primcount);
	}

	public void glMultiDrawArrays(int mode, IntBuffer first, IntBuffer count,
			int primcount) {
		delegate.glMultiDrawArrays(mode, first, count, primcount);
	}

	public void glMultiDrawArraysEXT(int mode, int[] first, int first_offset,
			int[] count, int count_offset, int primcount) {
		delegate.glMultiDrawArraysEXT(mode, first, first_offset, count,
				count_offset, primcount);
	}

	public void glMultiDrawArraysEXT(int mode, IntBuffer first,
			IntBuffer count, int primcount) {
		delegate.glMultiDrawArraysEXT(mode, first, count, primcount);
	}

	public void glMultiDrawElementArrayAPPLE(int mode, int[] first,
			int first_offset, int[] count, int count_offset, int primcount) {
		delegate.glMultiDrawElementArrayAPPLE(mode, first, first_offset, count,
				count_offset, primcount);
	}

	public void glMultiDrawElementArrayAPPLE(int mode, IntBuffer first,
			IntBuffer count, int primcount) {
		delegate.glMultiDrawElementArrayAPPLE(mode, first, count, primcount);
	}

	public void glMultiDrawElements(int mode, int[] count, int count_offset,
			int type, Buffer[] indices, int primcount) {
		delegate.glMultiDrawElements(mode, count, count_offset, type, indices,
				primcount);
	}

	public void glMultiDrawElements(int mode, IntBuffer count, int type,
			Buffer[] indices, int primcount) {
		delegate.glMultiDrawElements(mode, count, type, indices, primcount);
	}

	public void glMultiDrawElementsEXT(int mode, int[] count, int count_offset,
			int type, Buffer[] indices, int primcount) {
		delegate.glMultiDrawElementsEXT(mode, count, count_offset, type,
				indices, primcount);
	}

	public void glMultiDrawElementsEXT(int mode, IntBuffer count, int type,
			Buffer[] indices, int primcount) {
		delegate.glMultiDrawElementsEXT(mode, count, type, indices, primcount);
	}

	public void glMultiDrawRangeElementArrayAPPLE(int mode, int start, int end,
			int[] first, int first_offset, int[] count, int count_offset,
			int primcount) {
		delegate.glMultiDrawRangeElementArrayAPPLE(mode, start, end, first,
				first_offset, count, count_offset, primcount);
	}

	public void glMultiDrawRangeElementArrayAPPLE(int mode, int start, int end,
			IntBuffer first, IntBuffer count, int primcount) {
		delegate.glMultiDrawRangeElementArrayAPPLE(mode, start, end, first,
				count, primcount);
	}

	public void glMultiModeDrawArraysIBM(int[] mode, int mode_offset,
			int[] first, int first_offset, int[] count, int count_offset,
			int primcount, int modestride) {
		delegate.glMultiModeDrawArraysIBM(mode, mode_offset, first,
				first_offset, count, count_offset, primcount, modestride);
	}

	public void glMultiModeDrawArraysIBM(IntBuffer mode, IntBuffer first,
			IntBuffer count, int primcount, int modestride) {
		delegate.glMultiModeDrawArraysIBM(mode, first, count, primcount,
				modestride);
	}

	public void glMultiModeDrawElementsIBM(int[] mode, int mode_offset,
			int[] count, int count_offset, int type, Buffer[] indices,
			int primcount, int modestride) {
		delegate.glMultiModeDrawElementsIBM(mode, mode_offset, count,
				count_offset, type, indices, primcount, modestride);
	}

	public void glMultiModeDrawElementsIBM(IntBuffer mode, IntBuffer count,
			int type, Buffer[] indices, int primcount, int modestride) {
		delegate.glMultiModeDrawElementsIBM(mode, count, type, indices,
				primcount, modestride);
	}

	public void glMultiTexCoord1d(int target, double s) {
		delegate.glMultiTexCoord1d(target, s);
	}

	public void glMultiTexCoord1dv(int target, double[] v, int v_offset) {
		delegate.glMultiTexCoord1dv(target, v, v_offset);
	}

	public void glMultiTexCoord1dv(int target, DoubleBuffer v) {
		delegate.glMultiTexCoord1dv(target, v);
	}

	public void glMultiTexCoord1f(int target, float s) {
		delegate.glMultiTexCoord1f(target, s);
	}

	public void glMultiTexCoord1fv(int target, float[] v, int v_offset) {
		delegate.glMultiTexCoord1fv(target, v, v_offset);
	}

	public void glMultiTexCoord1fv(int target, FloatBuffer v) {
		delegate.glMultiTexCoord1fv(target, v);
	}

	public void glMultiTexCoord1hNV(int target, short s) {
		delegate.glMultiTexCoord1hNV(target, s);
	}

	public void glMultiTexCoord1hvNV(int index, short[] v, int v_offset) {
		delegate.glMultiTexCoord1hvNV(index, v, v_offset);
	}

	public void glMultiTexCoord1hvNV(int index, ShortBuffer v) {
		delegate.glMultiTexCoord1hvNV(index, v);
	}

	public void glMultiTexCoord1i(int target, int s) {
		delegate.glMultiTexCoord1i(target, s);
	}

	public void glMultiTexCoord1iv(int target, int[] v, int v_offset) {
		delegate.glMultiTexCoord1iv(target, v, v_offset);
	}

	public void glMultiTexCoord1iv(int target, IntBuffer v) {
		delegate.glMultiTexCoord1iv(target, v);
	}

	public void glMultiTexCoord1s(int target, short s) {
		delegate.glMultiTexCoord1s(target, s);
	}

	public void glMultiTexCoord1sv(int target, short[] v, int v_offset) {
		delegate.glMultiTexCoord1sv(target, v, v_offset);
	}

	public void glMultiTexCoord1sv(int target, ShortBuffer v) {
		delegate.glMultiTexCoord1sv(target, v);
	}

	public void glMultiTexCoord2d(int target, double s, double t) {
		delegate.glMultiTexCoord2d(target, s, t);
	}

	public void glMultiTexCoord2dv(int target, double[] v, int v_offset) {
		delegate.glMultiTexCoord2dv(target, v, v_offset);
	}

	public void glMultiTexCoord2dv(int target, DoubleBuffer v) {
		delegate.glMultiTexCoord2dv(target, v);
	}

	public void glMultiTexCoord2f(int target, float s, float t) {
		delegate.glMultiTexCoord2f(target, s, t);
	}

	public void glMultiTexCoord2fv(int target, float[] v, int v_offset) {
		delegate.glMultiTexCoord2fv(target, v, v_offset);
	}

	public void glMultiTexCoord2fv(int target, FloatBuffer v) {
		delegate.glMultiTexCoord2fv(target, v);
	}

	public void glMultiTexCoord2hNV(int target, short s, short t) {
		delegate.glMultiTexCoord2hNV(target, s, t);
	}

	public void glMultiTexCoord2hvNV(int index, short[] v, int v_offset) {
		delegate.glMultiTexCoord2hvNV(index, v, v_offset);
	}

	public void glMultiTexCoord2hvNV(int index, ShortBuffer v) {
		delegate.glMultiTexCoord2hvNV(index, v);
	}

	public void glMultiTexCoord2i(int target, int s, int t) {
		delegate.glMultiTexCoord2i(target, s, t);
	}

	public void glMultiTexCoord2iv(int target, int[] v, int v_offset) {
		delegate.glMultiTexCoord2iv(target, v, v_offset);
	}

	public void glMultiTexCoord2iv(int target, IntBuffer v) {
		delegate.glMultiTexCoord2iv(target, v);
	}

	public void glMultiTexCoord2s(int target, short s, short t) {
		delegate.glMultiTexCoord2s(target, s, t);
	}

	public void glMultiTexCoord2sv(int target, short[] v, int v_offset) {
		delegate.glMultiTexCoord2sv(target, v, v_offset);
	}

	public void glMultiTexCoord2sv(int target, ShortBuffer v) {
		delegate.glMultiTexCoord2sv(target, v);
	}

	public void glMultiTexCoord3d(int target, double s, double t, double r) {
		delegate.glMultiTexCoord3d(target, s, t, r);
	}

	public void glMultiTexCoord3dv(int target, double[] v, int v_offset) {
		delegate.glMultiTexCoord3dv(target, v, v_offset);
	}

	public void glMultiTexCoord3dv(int target, DoubleBuffer v) {
		delegate.glMultiTexCoord3dv(target, v);
	}

	public void glMultiTexCoord3f(int target, float s, float t, float r) {
		delegate.glMultiTexCoord3f(target, s, t, r);
	}

	public void glMultiTexCoord3fv(int target, float[] v, int v_offset) {
		delegate.glMultiTexCoord3fv(target, v, v_offset);
	}

	public void glMultiTexCoord3fv(int target, FloatBuffer v) {
		delegate.glMultiTexCoord3fv(target, v);
	}

	public void glMultiTexCoord3hNV(int target, short s, short t, short r) {
		delegate.glMultiTexCoord3hNV(target, s, t, r);
	}

	public void glMultiTexCoord3hvNV(int index, short[] v, int v_offset) {
		delegate.glMultiTexCoord3hvNV(index, v, v_offset);
	}

	public void glMultiTexCoord3hvNV(int index, ShortBuffer v) {
		delegate.glMultiTexCoord3hvNV(index, v);
	}

	public void glMultiTexCoord3i(int target, int s, int t, int r) {
		delegate.glMultiTexCoord3i(target, s, t, r);
	}

	public void glMultiTexCoord3iv(int target, int[] v, int v_offset) {
		delegate.glMultiTexCoord3iv(target, v, v_offset);
	}

	public void glMultiTexCoord3iv(int target, IntBuffer v) {
		delegate.glMultiTexCoord3iv(target, v);
	}

	public void glMultiTexCoord3s(int target, short s, short t, short r) {
		delegate.glMultiTexCoord3s(target, s, t, r);
	}

	public void glMultiTexCoord3sv(int target, short[] v, int v_offset) {
		delegate.glMultiTexCoord3sv(target, v, v_offset);
	}

	public void glMultiTexCoord3sv(int target, ShortBuffer v) {
		delegate.glMultiTexCoord3sv(target, v);
	}

	public void glMultiTexCoord4d(int target, double s, double t, double r,
			double q) {
		delegate.glMultiTexCoord4d(target, s, t, r, q);
	}

	public void glMultiTexCoord4dv(int target, double[] v, int v_offset) {
		delegate.glMultiTexCoord4dv(target, v, v_offset);
	}

	public void glMultiTexCoord4dv(int target, DoubleBuffer v) {
		delegate.glMultiTexCoord4dv(target, v);
	}

	public void glMultiTexCoord4f(int target, float s, float t, float r, float q) {
		delegate.glMultiTexCoord4f(target, s, t, r, q);
	}

	public void glMultiTexCoord4fv(int target, float[] v, int v_offset) {
		delegate.glMultiTexCoord4fv(target, v, v_offset);
	}

	public void glMultiTexCoord4fv(int target, FloatBuffer v) {
		delegate.glMultiTexCoord4fv(target, v);
	}

	public void glMultiTexCoord4hNV(int target, short s, short t, short r,
			short q) {
		delegate.glMultiTexCoord4hNV(target, s, t, r, q);
	}

	public void glMultiTexCoord4hvNV(int index, short[] v, int v_offset) {
		delegate.glMultiTexCoord4hvNV(index, v, v_offset);
	}

	public void glMultiTexCoord4hvNV(int index, ShortBuffer v) {
		delegate.glMultiTexCoord4hvNV(index, v);
	}

	public void glMultiTexCoord4i(int target, int start, int x, int y, int width) {
		delegate.glMultiTexCoord4i(target, start, x, y, width);
	}

	public void glMultiTexCoord4iv(int target, int[] v, int v_offset) {
		delegate.glMultiTexCoord4iv(target, v, v_offset);
	}

	public void glMultiTexCoord4iv(int target, IntBuffer v) {
		delegate.glMultiTexCoord4iv(target, v);
	}

	public void glMultiTexCoord4s(int target, short s, short t, short r, short q) {
		delegate.glMultiTexCoord4s(target, s, t, r, q);
	}

	public void glMultiTexCoord4sv(int target, short[] v, int v_offset) {
		delegate.glMultiTexCoord4sv(target, v, v_offset);
	}

	public void glMultiTexCoord4sv(int target, ShortBuffer v) {
		delegate.glMultiTexCoord4sv(target, v);
	}

	public void glMultMatrixd(double[] m, int m_offset) {
		delegate.glMultMatrixd(m, m_offset);
	}

	public void glMultMatrixd(DoubleBuffer m) {
		delegate.glMultMatrixd(m);
	}

	public void glMultMatrixf(float[] m, int m_offset) {
		delegate.glMultMatrixf(m, m_offset);
	}

	public void glMultMatrixf(FloatBuffer m) {
		delegate.glMultMatrixf(m);
	}

	public void glMultTransposeMatrixd(double[] m, int m_offset) {
		delegate.glMultTransposeMatrixd(m, m_offset);
	}

	public void glMultTransposeMatrixd(DoubleBuffer m) {
		delegate.glMultTransposeMatrixd(m);
	}

	public void glMultTransposeMatrixf(float[] m, int m_offset) {
		delegate.glMultTransposeMatrixf(m, m_offset);
	}

	public void glMultTransposeMatrixf(FloatBuffer m) {
		delegate.glMultTransposeMatrixf(m);
	}

	public int glNewBufferRegion(int type) {
		return delegate.glNewBufferRegion(type);
	}

	public void glNewList(int list, int mode) {
		delegate.glNewList(list, mode);
	}

	public int glNewObjectBufferATI(int size, Buffer pointer, int usage) {
		return delegate.glNewObjectBufferATI(size, pointer, usage);
	}

	public void glNormal3b(byte nx, byte ny, byte nz) {
		delegate.glNormal3b(nx, ny, nz);
	}

	public void glNormal3bv(byte[] v, int v_offset) {
		delegate.glNormal3bv(v, v_offset);
	}

	public void glNormal3bv(ByteBuffer v) {
		delegate.glNormal3bv(v);
	}

	public void glNormal3d(double nx, double ny, double nz) {
		delegate.glNormal3d(nx, ny, nz);
	}

	public void glNormal3dv(double[] v, int v_offset) {
		delegate.glNormal3dv(v, v_offset);
	}

	public void glNormal3dv(DoubleBuffer v) {
		delegate.glNormal3dv(v);
	}

	public void glNormal3f(float nx, float ny, float nz) {
		delegate.glNormal3f(nx, ny, nz);
	}

	public void glNormal3fv(float[] v, int v_offset) {
		delegate.glNormal3fv(v, v_offset);
	}

	public void glNormal3fv(FloatBuffer v) {
		delegate.glNormal3fv(v);
	}

	public void glNormal3fVertex3fSUN(float r, float g, float b, float x,
			float y, float z) {
		delegate.glNormal3fVertex3fSUN(r, g, b, x, y, z);
	}

	public void glNormal3fVertex3fvSUN(float[] c, int c_offset, float[] v,
			int v_offset) {
		delegate.glNormal3fVertex3fvSUN(c, c_offset, v, v_offset);
	}

	public void glNormal3fVertex3fvSUN(FloatBuffer c, FloatBuffer v) {
		delegate.glNormal3fVertex3fvSUN(c, v);
	}

	public void glNormal3hNV(short red, short green, short blue) {
		delegate.glNormal3hNV(red, green, blue);
	}

	public void glNormal3hvNV(short[] v, int v_offset) {
		delegate.glNormal3hvNV(v, v_offset);
	}

	public void glNormal3hvNV(ShortBuffer v) {
		delegate.glNormal3hvNV(v);
	}

	public void glNormal3i(int nx, int ny, int nz) {
		delegate.glNormal3i(nx, ny, nz);
	}

	public void glNormal3iv(int[] v, int v_offset) {
		delegate.glNormal3iv(v, v_offset);
	}

	public void glNormal3iv(IntBuffer v) {
		delegate.glNormal3iv(v);
	}

	public void glNormal3s(short nx, short ny, short nz) {
		delegate.glNormal3s(nx, ny, nz);
	}

	public void glNormal3sv(short[] v, int v_offset) {
		delegate.glNormal3sv(v, v_offset);
	}

	public void glNormal3sv(ShortBuffer v) {
		delegate.glNormal3sv(v);
	}

	public void glNormalPointer(int type, int stride, Buffer ptr) {
		delegate.glNormalPointer(type, stride, ptr);
	}

	public void glNormalPointer(int type, int stride, long ptr_buffer_offset) {
		delegate.glNormalPointer(type, stride, ptr_buffer_offset);
	}

	public void glNormalStream3bATI(int stream, byte nx, byte ny, byte nz) {
		delegate.glNormalStream3bATI(stream, nx, ny, nz);
	}

	public void glNormalStream3bvATI(int index, byte[] v, int v_offset) {
		delegate.glNormalStream3bvATI(index, v, v_offset);
	}

	public void glNormalStream3bvATI(int index, ByteBuffer v) {
		delegate.glNormalStream3bvATI(index, v);
	}

	public void glNormalStream3dATI(int target, double s, double t, double r) {
		delegate.glNormalStream3dATI(target, s, t, r);
	}

	public void glNormalStream3dvATI(int target, double[] v, int v_offset) {
		delegate.glNormalStream3dvATI(target, v, v_offset);
	}

	public void glNormalStream3dvATI(int target, DoubleBuffer v) {
		delegate.glNormalStream3dvATI(target, v);
	}

	public void glNormalStream3fATI(int target, float s, float t, float r) {
		delegate.glNormalStream3fATI(target, s, t, r);
	}

	public void glNormalStream3fvATI(int target, float[] v, int v_offset) {
		delegate.glNormalStream3fvATI(target, v, v_offset);
	}

	public void glNormalStream3fvATI(int target, FloatBuffer v) {
		delegate.glNormalStream3fvATI(target, v);
	}

	public void glNormalStream3iATI(int target, int s, int t, int r) {
		delegate.glNormalStream3iATI(target, s, t, r);
	}

	public void glNormalStream3ivATI(int target, int[] v, int v_offset) {
		delegate.glNormalStream3ivATI(target, v, v_offset);
	}

	public void glNormalStream3ivATI(int target, IntBuffer v) {
		delegate.glNormalStream3ivATI(target, v);
	}

	public void glNormalStream3sATI(int target, short s, short t, short r) {
		delegate.glNormalStream3sATI(target, s, t, r);
	}

	public void glNormalStream3svATI(int target, short[] v, int v_offset) {
		delegate.glNormalStream3svATI(target, v, v_offset);
	}

	public void glNormalStream3svATI(int target, ShortBuffer v) {
		delegate.glNormalStream3svATI(target, v);
	}

	public void glOrtho(double left, double right, double bottom, double top,
			double near_val, double far_val) {
		delegate.glOrtho(left, right, bottom, top, near_val, far_val);
	}

	public void glPassTexCoordATI(int red, int green, int blue) {
		delegate.glPassTexCoordATI(red, green, blue);
	}

	public void glPassThrough(float token) {
		delegate.glPassThrough(token);
	}

	public void glPixelDataRangeNV(int target, int level, Buffer img) {
		delegate.glPixelDataRangeNV(target, level, img);
	}

	public void glPixelMapfv(int map, int mapsize, float[] values,
			int values_offset) {
		delegate.glPixelMapfv(map, mapsize, values, values_offset);
	}

	public void glPixelMapfv(int map, int mapsize, FloatBuffer values) {
		delegate.glPixelMapfv(map, mapsize, values);
	}

	public void glPixelMapfv(int map, int mapsize, long values_buffer_offset) {
		delegate.glPixelMapfv(map, mapsize, values_buffer_offset);
	}

	public void glPixelMapuiv(int map, int mapsize, int[] values,
			int values_offset) {
		delegate.glPixelMapuiv(map, mapsize, values, values_offset);
	}

	public void glPixelMapuiv(int map, int mapsize, IntBuffer values) {
		delegate.glPixelMapuiv(map, mapsize, values);
	}

	public void glPixelMapuiv(int map, int mapsize, long values_buffer_offset) {
		delegate.glPixelMapuiv(map, mapsize, values_buffer_offset);
	}

	public void glPixelMapusv(int map, int mapsize, long values_buffer_offset) {
		delegate.glPixelMapusv(map, mapsize, values_buffer_offset);
	}

	public void glPixelMapusv(int map, int mapsize, short[] values,
			int values_offset) {
		delegate.glPixelMapusv(map, mapsize, values, values_offset);
	}

	public void glPixelMapusv(int map, int mapsize, ShortBuffer values) {
		delegate.glPixelMapusv(map, mapsize, values);
	}

	public void glPixelStoref(int pname, float param) {
		delegate.glPixelStoref(pname, param);
	}

	public void glPixelStorei(int pname, int param) {
		delegate.glPixelStorei(pname, param);
	}

	public void glPixelTexGenParameterfSGIS(int target, float s) {
		delegate.glPixelTexGenParameterfSGIS(target, s);
	}

	public void glPixelTexGenParameterfvSGIS(int target, float[] v, int v_offset) {
		delegate.glPixelTexGenParameterfvSGIS(target, v, v_offset);
	}

	public void glPixelTexGenParameterfvSGIS(int target, FloatBuffer v) {
		delegate.glPixelTexGenParameterfvSGIS(target, v);
	}

	public void glPixelTexGenParameteriSGIS(int target, int s) {
		delegate.glPixelTexGenParameteriSGIS(target, s);
	}

	public void glPixelTexGenParameterivSGIS(int target, int[] v, int v_offset) {
		delegate.glPixelTexGenParameterivSGIS(target, v, v_offset);
	}

	public void glPixelTexGenParameterivSGIS(int target, IntBuffer v) {
		delegate.glPixelTexGenParameterivSGIS(target, v);
	}

	public void glPixelTexGenSGIX(int mode) {
		delegate.glPixelTexGenSGIX(mode);
	}

	public void glPixelTransferf(int pname, float param) {
		delegate.glPixelTransferf(pname, param);
	}

	public void glPixelTransferi(int pname, int param) {
		delegate.glPixelTransferi(pname, param);
	}

	public void glPixelTransformParameterfEXT(int target, int pname,
			float params) {
		delegate.glPixelTransformParameterfEXT(target, pname, params);
	}

	public void glPixelTransformParameterfvEXT(int target, int pname,
			float[] params, int params_offset) {
		delegate.glPixelTransformParameterfvEXT(target, pname, params,
				params_offset);
	}

	public void glPixelTransformParameterfvEXT(int target, int pname,
			FloatBuffer params) {
		delegate.glPixelTransformParameterfvEXT(target, pname, params);
	}

	public void glPixelTransformParameteriEXT(int target, int pname, int params) {
		delegate.glPixelTransformParameteriEXT(target, pname, params);
	}

	public void glPixelTransformParameterivEXT(int target, int pname,
			int[] params, int params_offset) {
		delegate.glPixelTransformParameterivEXT(target, pname, params,
				params_offset);
	}

	public void glPixelTransformParameterivEXT(int target, int pname,
			IntBuffer params) {
		delegate.glPixelTransformParameterivEXT(target, pname, params);
	}

	public void glPixelZoom(float xfactor, float yfactor) {
		delegate.glPixelZoom(xfactor, yfactor);
	}

	public void glPNTrianglesfATI(int target, float s) {
		delegate.glPNTrianglesfATI(target, s);
	}

	public void glPNTrianglesiATI(int target, int s) {
		delegate.glPNTrianglesiATI(target, s);
	}

	public void glPointParameterf(int target, float s) {
		delegate.glPointParameterf(target, s);
	}

	public void glPointParameterfARB(int target, float s) {
		delegate.glPointParameterfARB(target, s);
	}

	public void glPointParameterfEXT(int target, float s) {
		delegate.glPointParameterfEXT(target, s);
	}

	public void glPointParameterfSGIS(int target, float s) {
		delegate.glPointParameterfSGIS(target, s);
	}

	public void glPointParameterfv(int target, float[] v, int v_offset) {
		delegate.glPointParameterfv(target, v, v_offset);
	}

	public void glPointParameterfv(int target, FloatBuffer v) {
		delegate.glPointParameterfv(target, v);
	}

	public void glPointParameterfvARB(int target, float[] v, int v_offset) {
		delegate.glPointParameterfvARB(target, v, v_offset);
	}

	public void glPointParameterfvARB(int target, FloatBuffer v) {
		delegate.glPointParameterfvARB(target, v);
	}

	public void glPointParameterfvEXT(int target, float[] v, int v_offset) {
		delegate.glPointParameterfvEXT(target, v, v_offset);
	}

	public void glPointParameterfvEXT(int target, FloatBuffer v) {
		delegate.glPointParameterfvEXT(target, v);
	}

	public void glPointParameterfvSGIS(int target, float[] v, int v_offset) {
		delegate.glPointParameterfvSGIS(target, v, v_offset);
	}

	public void glPointParameterfvSGIS(int target, FloatBuffer v) {
		delegate.glPointParameterfvSGIS(target, v);
	}

	public void glPointParameteri(int target, int s) {
		delegate.glPointParameteri(target, s);
	}

	public void glPointParameteriNV(int target, int s) {
		delegate.glPointParameteriNV(target, s);
	}

	public void glPointParameteriv(int target, int[] v, int v_offset) {
		delegate.glPointParameteriv(target, v, v_offset);
	}

	public void glPointParameteriv(int target, IntBuffer v) {
		delegate.glPointParameteriv(target, v);
	}

	public void glPointParameterivNV(int target, int[] v, int v_offset) {
		delegate.glPointParameterivNV(target, v, v_offset);
	}

	public void glPointParameterivNV(int target, IntBuffer v) {
		delegate.glPointParameterivNV(target, v);
	}

	public void glPointSize(float size) {
		delegate.glPointSize(size);
	}

	public int glPollAsyncSGIX(int[] markerp, int markerp_offset) {
		return delegate.glPollAsyncSGIX(markerp, markerp_offset);
	}

	public int glPollAsyncSGIX(IntBuffer markerp) {
		return delegate.glPollAsyncSGIX(markerp);
	}

	public int glPollInstrumentsSGIX(int[] marker_p, int marker_p_offset) {
		return delegate.glPollInstrumentsSGIX(marker_p, marker_p_offset);
	}

	public int glPollInstrumentsSGIX(IntBuffer marker_p) {
		return delegate.glPollInstrumentsSGIX(marker_p);
	}

	public void glPolygonMode(int face, int mode) {
		delegate.glPolygonMode(face, mode);
	}

	public void glPolygonOffset(float factor, float units) {
		delegate.glPolygonOffset(factor, units);
	}

	public void glPolygonStipple(byte[] mask, int mask_offset) {
		delegate.glPolygonStipple(mask, mask_offset);
	}

	public void glPolygonStipple(ByteBuffer mask) {
		delegate.glPolygonStipple(mask);
	}

	public void glPolygonStipple(long mask_buffer_offset) {
		delegate.glPolygonStipple(mask_buffer_offset);
	}

	public void glPopAttrib() {
		delegate.glPopAttrib();
	}

	public void glPopClientAttrib() {
		delegate.glPopClientAttrib();
	}

	public void glPopMatrix() {
		delegate.glPopMatrix();
	}

	public void glPopName() {
		delegate.glPopName();
	}

	public void glPrimitiveRestartIndexNV(int mode) {
		delegate.glPrimitiveRestartIndexNV(mode);
	}

	public void glPrimitiveRestartNV() {
		delegate.glPrimitiveRestartNV();
	}

	public void glPrioritizeTextures(int n, int[] textures,
			int textures_offset, float[] priorities, int priorities_offset) {
		delegate.glPrioritizeTextures(n, textures, textures_offset, priorities,
				priorities_offset);
	}

	public void glPrioritizeTextures(int n, IntBuffer textures,
			FloatBuffer priorities) {
		delegate.glPrioritizeTextures(n, textures, priorities);
	}

	public void glProgramBufferParametersfvNV(int target, int buffer,
			int index, int count, float[] params, int params_offset) {
		delegate.glProgramBufferParametersfvNV(target, buffer, index, count,
				params, params_offset);
	}

	public void glProgramBufferParametersfvNV(int target, int buffer,
			int index, int count, FloatBuffer params) {
		delegate.glProgramBufferParametersfvNV(target, buffer, index, count,
				params);
	}

	public void glProgramBufferParametersIivNV(int target, int buffer,
			int index, int count, int[] params, int params_offset) {
		delegate.glProgramBufferParametersIivNV(target, buffer, index, count,
				params, params_offset);
	}

	public void glProgramBufferParametersIivNV(int target, int buffer,
			int index, int count, IntBuffer params) {
		delegate.glProgramBufferParametersIivNV(target, buffer, index, count,
				params);
	}

	public void glProgramBufferParametersIuivNV(int target, int buffer,
			int index, int count, int[] params, int params_offset) {
		delegate.glProgramBufferParametersIuivNV(target, buffer, index, count,
				params, params_offset);
	}

	public void glProgramBufferParametersIuivNV(int target, int buffer,
			int index, int count, IntBuffer params) {
		delegate.glProgramBufferParametersIuivNV(target, buffer, index, count,
				params);
	}

	public void glProgramEnvParameter4dARB(int target, int index, double x,
			double y, double z, double w) {
		delegate.glProgramEnvParameter4dARB(target, index, x, y, z, w);
	}

	public void glProgramEnvParameter4dvARB(int target, int index,
			double[] params, int params_offset) {
		delegate.glProgramEnvParameter4dvARB(target, index, params,
				params_offset);
	}

	public void glProgramEnvParameter4dvARB(int target, int index,
			DoubleBuffer params) {
		delegate.glProgramEnvParameter4dvARB(target, index, params);
	}

	public void glProgramEnvParameter4fARB(int target, int index, float x,
			float y, float z, float w) {
		delegate.glProgramEnvParameter4fARB(target, index, x, y, z, w);
	}

	public void glProgramEnvParameter4fvARB(int target, int pname,
			float[] params, int params_offset) {
		delegate.glProgramEnvParameter4fvARB(target, pname, params,
				params_offset);
	}

	public void glProgramEnvParameter4fvARB(int target, int pname,
			FloatBuffer params) {
		delegate.glProgramEnvParameter4fvARB(target, pname, params);
	}

	public void glProgramEnvParameterI4iNV(int target, int index, int x, int y,
			int z, int w) {
		delegate.glProgramEnvParameterI4iNV(target, index, x, y, z, w);
	}

	public void glProgramEnvParameterI4ivNV(int target, int index,
			int[] params, int params_offset) {
		delegate.glProgramEnvParameterI4ivNV(target, index, params,
				params_offset);
	}

	public void glProgramEnvParameterI4ivNV(int target, int index,
			IntBuffer params) {
		delegate.glProgramEnvParameterI4ivNV(target, index, params);
	}

	public void glProgramEnvParameterI4uiNV(int target, int index, int x,
			int y, int z, int w) {
		delegate.glProgramEnvParameterI4uiNV(target, index, x, y, z, w);
	}

	public void glProgramEnvParameterI4uivNV(int target, int index,
			int[] params, int params_offset) {
		delegate.glProgramEnvParameterI4uivNV(target, index, params,
				params_offset);
	}

	public void glProgramEnvParameterI4uivNV(int target, int index,
			IntBuffer params) {
		delegate.glProgramEnvParameterI4uivNV(target, index, params);
	}

	public void glProgramEnvParameters4fvEXT(int target, int filter, int n,
			float[] weights, int weights_offset) {
		delegate.glProgramEnvParameters4fvEXT(target, filter, n, weights,
				weights_offset);
	}

	public void glProgramEnvParameters4fvEXT(int target, int filter, int n,
			FloatBuffer weights) {
		delegate.glProgramEnvParameters4fvEXT(target, filter, n, weights);
	}

	public void glProgramEnvParametersI4ivNV(int target, int index, int count,
			int[] params, int params_offset) {
		delegate.glProgramEnvParametersI4ivNV(target, index, count, params,
				params_offset);
	}

	public void glProgramEnvParametersI4ivNV(int target, int index, int count,
			IntBuffer params) {
		delegate.glProgramEnvParametersI4ivNV(target, index, count, params);
	}

	public void glProgramEnvParametersI4uivNV(int target, int index, int count,
			int[] params, int params_offset) {
		delegate.glProgramEnvParametersI4uivNV(target, index, count, params,
				params_offset);
	}

	public void glProgramEnvParametersI4uivNV(int target, int index, int count,
			IntBuffer params) {
		delegate.glProgramEnvParametersI4uivNV(target, index, count, params);
	}

	public void glProgramLocalParameter4dARB(int target, int index, double x,
			double y, double z, double w) {
		delegate.glProgramLocalParameter4dARB(target, index, x, y, z, w);
	}

	public void glProgramLocalParameter4dvARB(int target, int index,
			double[] params, int params_offset) {
		delegate.glProgramLocalParameter4dvARB(target, index, params,
				params_offset);
	}

	public void glProgramLocalParameter4dvARB(int target, int index,
			DoubleBuffer params) {
		delegate.glProgramLocalParameter4dvARB(target, index, params);
	}

	public void glProgramLocalParameter4fARB(int target, int index, float x,
			float y, float z, float w) {
		delegate.glProgramLocalParameter4fARB(target, index, x, y, z, w);
	}

	public void glProgramLocalParameter4fvARB(int target, int pname,
			float[] params, int params_offset) {
		delegate.glProgramLocalParameter4fvARB(target, pname, params,
				params_offset);
	}

	public void glProgramLocalParameter4fvARB(int target, int pname,
			FloatBuffer params) {
		delegate.glProgramLocalParameter4fvARB(target, pname, params);
	}

	public void glProgramLocalParameterI4iNV(int target, int index, int x,
			int y, int z, int w) {
		delegate.glProgramLocalParameterI4iNV(target, index, x, y, z, w);
	}

	public void glProgramLocalParameterI4ivNV(int target, int index,
			int[] params, int params_offset) {
		delegate.glProgramLocalParameterI4ivNV(target, index, params,
				params_offset);
	}

	public void glProgramLocalParameterI4ivNV(int target, int index,
			IntBuffer params) {
		delegate.glProgramLocalParameterI4ivNV(target, index, params);
	}

	public void glProgramLocalParameterI4uiNV(int target, int index, int x,
			int y, int z, int w) {
		delegate.glProgramLocalParameterI4uiNV(target, index, x, y, z, w);
	}

	public void glProgramLocalParameterI4uivNV(int target, int index,
			int[] params, int params_offset) {
		delegate.glProgramLocalParameterI4uivNV(target, index, params,
				params_offset);
	}

	public void glProgramLocalParameterI4uivNV(int target, int index,
			IntBuffer params) {
		delegate.glProgramLocalParameterI4uivNV(target, index, params);
	}

	public void glProgramLocalParameters4fvEXT(int target, int filter, int n,
			float[] weights, int weights_offset) {
		delegate.glProgramLocalParameters4fvEXT(target, filter, n, weights,
				weights_offset);
	}

	public void glProgramLocalParameters4fvEXT(int target, int filter, int n,
			FloatBuffer weights) {
		delegate.glProgramLocalParameters4fvEXT(target, filter, n, weights);
	}

	public void glProgramLocalParametersI4ivNV(int target, int index,
			int count, int[] params, int params_offset) {
		delegate.glProgramLocalParametersI4ivNV(target, index, count, params,
				params_offset);
	}

	public void glProgramLocalParametersI4ivNV(int target, int index,
			int count, IntBuffer params) {
		delegate.glProgramLocalParametersI4ivNV(target, index, count, params);
	}

	public void glProgramLocalParametersI4uivNV(int target, int index,
			int count, int[] params, int params_offset) {
		delegate.glProgramLocalParametersI4uivNV(target, index, count, params,
				params_offset);
	}

	public void glProgramLocalParametersI4uivNV(int target, int index,
			int count, IntBuffer params) {
		delegate.glProgramLocalParametersI4uivNV(target, index, count, params);
	}

	public void glProgramNamedParameter4dNV(int id, int len, String name,
			double x, double y, double z, double w) {
		delegate.glProgramNamedParameter4dNV(id, len, name, x, y, z, w);
	}

	public void glProgramNamedParameter4dvNV(int id, int len, String name,
			double[] v, int v_offset) {
		delegate.glProgramNamedParameter4dvNV(id, len, name, v, v_offset);
	}

	public void glProgramNamedParameter4dvNV(int id, int len, String name,
			DoubleBuffer v) {
		delegate.glProgramNamedParameter4dvNV(id, len, name, v);
	}

	public void glProgramNamedParameter4fNV(int id, int len, String name,
			float x, float y, float z, float w) {
		delegate.glProgramNamedParameter4fNV(id, len, name, x, y, z, w);
	}

	public void glProgramNamedParameter4fvNV(int id, int len, String name,
			float[] v, int v_offset) {
		delegate.glProgramNamedParameter4fvNV(id, len, name, v, v_offset);
	}

	public void glProgramNamedParameter4fvNV(int id, int len, String name,
			FloatBuffer v) {
		delegate.glProgramNamedParameter4fvNV(id, len, name, v);
	}

	public void glProgramParameter4dNV(int target, int index, double x,
			double y, double z, double w) {
		delegate.glProgramParameter4dNV(target, index, x, y, z, w);
	}

	public void glProgramParameter4dvNV(int target, int index, double[] params,
			int params_offset) {
		delegate.glProgramParameter4dvNV(target, index, params, params_offset);
	}

	public void glProgramParameter4dvNV(int target, int index,
			DoubleBuffer params) {
		delegate.glProgramParameter4dvNV(target, index, params);
	}

	public void glProgramParameter4fNV(int target, int index, float x, float y,
			float z, float w) {
		delegate.glProgramParameter4fNV(target, index, x, y, z, w);
	}

	public void glProgramParameter4fvNV(int target, int pname, float[] params,
			int params_offset) {
		delegate.glProgramParameter4fvNV(target, pname, params, params_offset);
	}

	public void glProgramParameter4fvNV(int target, int pname,
			FloatBuffer params) {
		delegate.glProgramParameter4fvNV(target, pname, params);
	}

	public void glProgramParameteriEXT(int program, int pname, int value) {
		delegate.glProgramParameteriEXT(program, pname, value);
	}

	public void glProgramParameters4dvNV(int target, int index, int count,
			double[] v, int v_offset) {
		delegate.glProgramParameters4dvNV(target, index, count, v, v_offset);
	}

	public void glProgramParameters4dvNV(int target, int index, int count,
			DoubleBuffer v) {
		delegate.glProgramParameters4dvNV(target, index, count, v);
	}

	public void glProgramParameters4fvNV(int target, int index, int count,
			float[] v, int v_offset) {
		delegate.glProgramParameters4fvNV(target, index, count, v, v_offset);
	}

	public void glProgramParameters4fvNV(int target, int index, int count,
			FloatBuffer v) {
		delegate.glProgramParameters4fvNV(target, index, count, v);
	}

	public void glProgramStringARB(int target, int format, int len,
			String string) {
		delegate.glProgramStringARB(target, format, len, string);
	}

	public void glProgramVertexLimitNV(int target, int limit) {
		delegate.glProgramVertexLimitNV(target, limit);
	}

	public void glPushAttrib(int mask) {
		delegate.glPushAttrib(mask);
	}

	public void glPushClientAttrib(int mask) {
		delegate.glPushClientAttrib(mask);
	}

	public void glPushMatrix() {
		delegate.glPushMatrix();
	}

	public void glPushName(int name) {
		delegate.glPushName(name);
	}

	public void glRasterPos2d(double x, double y) {
		delegate.glRasterPos2d(x, y);
	}

	public void glRasterPos2dv(double[] v, int v_offset) {
		delegate.glRasterPos2dv(v, v_offset);
	}

	public void glRasterPos2dv(DoubleBuffer v) {
		delegate.glRasterPos2dv(v);
	}

	public void glRasterPos2f(float x, float y) {
		delegate.glRasterPos2f(x, y);
	}

	public void glRasterPos2fv(float[] v, int v_offset) {
		delegate.glRasterPos2fv(v, v_offset);
	}

	public void glRasterPos2fv(FloatBuffer v) {
		delegate.glRasterPos2fv(v);
	}

	public void glRasterPos2i(int x, int y) {
		delegate.glRasterPos2i(x, y);
	}

	public void glRasterPos2iv(int[] v, int v_offset) {
		delegate.glRasterPos2iv(v, v_offset);
	}

	public void glRasterPos2iv(IntBuffer v) {
		delegate.glRasterPos2iv(v);
	}

	public void glRasterPos2s(short x, short y) {
		delegate.glRasterPos2s(x, y);
	}

	public void glRasterPos2sv(short[] v, int v_offset) {
		delegate.glRasterPos2sv(v, v_offset);
	}

	public void glRasterPos2sv(ShortBuffer v) {
		delegate.glRasterPos2sv(v);
	}

	public void glRasterPos3d(double x, double y, double z) {
		delegate.glRasterPos3d(x, y, z);
	}

	public void glRasterPos3dv(double[] v, int v_offset) {
		delegate.glRasterPos3dv(v, v_offset);
	}

	public void glRasterPos3dv(DoubleBuffer v) {
		delegate.glRasterPos3dv(v);
	}

	public void glRasterPos3f(float x, float y, float z) {
		delegate.glRasterPos3f(x, y, z);
	}

	public void glRasterPos3fv(float[] v, int v_offset) {
		delegate.glRasterPos3fv(v, v_offset);
	}

	public void glRasterPos3fv(FloatBuffer v) {
		delegate.glRasterPos3fv(v);
	}

	public void glRasterPos3i(int x, int y, int z) {
		delegate.glRasterPos3i(x, y, z);
	}

	public void glRasterPos3iv(int[] v, int v_offset) {
		delegate.glRasterPos3iv(v, v_offset);
	}

	public void glRasterPos3iv(IntBuffer v) {
		delegate.glRasterPos3iv(v);
	}

	public void glRasterPos3s(short x, short y, short z) {
		delegate.glRasterPos3s(x, y, z);
	}

	public void glRasterPos3sv(short[] v, int v_offset) {
		delegate.glRasterPos3sv(v, v_offset);
	}

	public void glRasterPos3sv(ShortBuffer v) {
		delegate.glRasterPos3sv(v);
	}

	public void glRasterPos4d(double x, double y, double z, double w) {
		delegate.glRasterPos4d(x, y, z, w);
	}

	public void glRasterPos4dv(double[] v, int v_offset) {
		delegate.glRasterPos4dv(v, v_offset);
	}

	public void glRasterPos4dv(DoubleBuffer v) {
		delegate.glRasterPos4dv(v);
	}

	public void glRasterPos4f(float x, float y, float z, float w) {
		delegate.glRasterPos4f(x, y, z, w);
	}

	public void glRasterPos4fv(float[] v, int v_offset) {
		delegate.glRasterPos4fv(v, v_offset);
	}

	public void glRasterPos4fv(FloatBuffer v) {
		delegate.glRasterPos4fv(v);
	}

	public void glRasterPos4i(int x, int y, int z, int w) {
		delegate.glRasterPos4i(x, y, z, w);
	}

	public void glRasterPos4iv(int[] v, int v_offset) {
		delegate.glRasterPos4iv(v, v_offset);
	}

	public void glRasterPos4iv(IntBuffer v) {
		delegate.glRasterPos4iv(v);
	}

	public void glRasterPos4s(short x, short y, short z, short w) {
		delegate.glRasterPos4s(x, y, z, w);
	}

	public void glRasterPos4sv(short[] v, int v_offset) {
		delegate.glRasterPos4sv(v, v_offset);
	}

	public void glRasterPos4sv(ShortBuffer v) {
		delegate.glRasterPos4sv(v);
	}

	public void glReadBuffer(int mode) {
		delegate.glReadBuffer(mode);
	}

	public void glReadBufferRegion(int target, int start, int x, int y,
			int width) {
		delegate.glReadBufferRegion(target, start, x, y, width);
	}

	public void glReadInstrumentsSGIX(int count) {
		delegate.glReadInstrumentsSGIX(count);
	}

	public void glReadPixels(int x, int y, int width, int height, int format,
			int type, Buffer pixels) {
		delegate.glReadPixels(x, y, width, height, format, type, pixels);
	}

	public void glReadPixels(int x, int y, int width, int height, int format,
			int type, long pixels_buffer_offset) {
		delegate.glReadPixels(x, y, width, height, format, type,
				pixels_buffer_offset);
	}

	public void glRectd(double x1, double y1, double x2, double y2) {
		delegate.glRectd(x1, y1, x2, y2);
	}

	public void glRectdv(double[] v1, int v1_offset, double[] v2, int v2_offset) {
		delegate.glRectdv(v1, v1_offset, v2, v2_offset);
	}

	public void glRectdv(DoubleBuffer v1, DoubleBuffer v2) {
		delegate.glRectdv(v1, v2);
	}

	public void glRectf(float x1, float y1, float x2, float y2) {
		delegate.glRectf(x1, y1, x2, y2);
	}

	public void glRectfv(float[] v1, int v1_offset, float[] v2, int v2_offset) {
		delegate.glRectfv(v1, v1_offset, v2, v2_offset);
	}

	public void glRectfv(FloatBuffer v1, FloatBuffer v2) {
		delegate.glRectfv(v1, v2);
	}

	public void glRecti(int x1, int y1, int x2, int y2) {
		delegate.glRecti(x1, y1, x2, y2);
	}

	public void glRectiv(int[] v1, int v1_offset, int[] v2, int v2_offset) {
		delegate.glRectiv(v1, v1_offset, v2, v2_offset);
	}

	public void glRectiv(IntBuffer v1, IntBuffer v2) {
		delegate.glRectiv(v1, v2);
	}

	public void glRects(short x1, short y1, short x2, short y2) {
		delegate.glRects(x1, y1, x2, y2);
	}

	public void glRectsv(short[] v1, int v1_offset, short[] v2, int v2_offset) {
		delegate.glRectsv(v1, v1_offset, v2, v2_offset);
	}

	public void glRectsv(ShortBuffer v1, ShortBuffer v2) {
		delegate.glRectsv(v1, v2);
	}

	public void glReferencePlaneSGIX(double[] m, int m_offset) {
		delegate.glReferencePlaneSGIX(m, m_offset);
	}

	public void glReferencePlaneSGIX(DoubleBuffer m) {
		delegate.glReferencePlaneSGIX(m);
	}

	public void glRenderbufferStorageEXT(int target, int internalformat,
			int width, int height) {
		delegate
				.glRenderbufferStorageEXT(target, internalformat, width, height);
	}

	public void glRenderbufferStorageMultisampleCoverageNV(int target,
			int coverageSamples, int colorSamples, int internalformat,
			int width, int height) {
		delegate.glRenderbufferStorageMultisampleCoverageNV(target,
				coverageSamples, colorSamples, internalformat, width, height);
	}

	public void glRenderbufferStorageMultisampleEXT(int target, int samples,
			int internalformat, int width, int height) {
		delegate.glRenderbufferStorageMultisampleEXT(target, samples,
				internalformat, width, height);
	}

	public int glRenderMode(int mode) {
		return delegate.glRenderMode(mode);
	}

	public void glReplacementCodeuiColor3fVertex3fSUN(int rc, float r, float g,
			float b, float x, float y, float z) {
		delegate.glReplacementCodeuiColor3fVertex3fSUN(rc, r, g, b, x, y, z);
	}

	public void glReplacementCodeuiColor3fVertex3fvSUN(int[] rc, int rc_offset,
			float[] c, int c_offset, float[] v, int v_offset) {
		delegate.glReplacementCodeuiColor3fVertex3fvSUN(rc, rc_offset, c,
				c_offset, v, v_offset);
	}

	public void glReplacementCodeuiColor3fVertex3fvSUN(IntBuffer rc,
			FloatBuffer c, FloatBuffer v) {
		delegate.glReplacementCodeuiColor3fVertex3fvSUN(rc, c, v);
	}

	public void glReplacementCodeuiColor4fNormal3fVertex3fSUN(int rc, float r,
			float g, float b, float a, float nx, float ny, float nz, float x,
			float y, float z) {
		delegate.glReplacementCodeuiColor4fNormal3fVertex3fSUN(rc, r, g, b, a,
				nx, ny, nz, x, y, z);
	}

	public void glReplacementCodeuiColor4fNormal3fVertex3fvSUN(int[] rc,
			int rc_offset, float[] c, int c_offset, float[] n, int n_offset,
			float[] v, int v_offset) {
		delegate.glReplacementCodeuiColor4fNormal3fVertex3fvSUN(rc, rc_offset,
				c, c_offset, n, n_offset, v, v_offset);
	}

	public void glReplacementCodeuiColor4fNormal3fVertex3fvSUN(IntBuffer rc,
			FloatBuffer c, FloatBuffer n, FloatBuffer v) {
		delegate.glReplacementCodeuiColor4fNormal3fVertex3fvSUN(rc, c, n, v);
	}

	public void glReplacementCodeuiColor4ubVertex3fSUN(int rc, byte r, byte g,
			byte b, byte a, float x, float y, float z) {
		delegate
				.glReplacementCodeuiColor4ubVertex3fSUN(rc, r, g, b, a, x, y, z);
	}

	public void glReplacementCodeuiColor4ubVertex3fvSUN(int[] rc,
			int rc_offset, byte[] c, int c_offset, float[] v, int v_offset) {
		delegate.glReplacementCodeuiColor4ubVertex3fvSUN(rc, rc_offset, c,
				c_offset, v, v_offset);
	}

	public void glReplacementCodeuiColor4ubVertex3fvSUN(IntBuffer rc,
			ByteBuffer c, FloatBuffer v) {
		delegate.glReplacementCodeuiColor4ubVertex3fvSUN(rc, c, v);
	}

	public void glReplacementCodeuiNormal3fVertex3fSUN(int rc, float r,
			float g, float b, float x, float y, float z) {
		delegate.glReplacementCodeuiNormal3fVertex3fSUN(rc, r, g, b, x, y, z);
	}

	public void glReplacementCodeuiNormal3fVertex3fvSUN(int[] rc,
			int rc_offset, float[] c, int c_offset, float[] v, int v_offset) {
		delegate.glReplacementCodeuiNormal3fVertex3fvSUN(rc, rc_offset, c,
				c_offset, v, v_offset);
	}

	public void glReplacementCodeuiNormal3fVertex3fvSUN(IntBuffer rc,
			FloatBuffer c, FloatBuffer v) {
		delegate.glReplacementCodeuiNormal3fVertex3fvSUN(rc, c, v);
	}

	public void glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fSUN(int rc,
			float s, float t, float r, float g, float b, float a, float nx,
			float ny, float nz, float x, float y, float z) {
		delegate.glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fSUN(rc, s,
				t, r, g, b, a, nx, ny, nz, x, y, z);
	}

	public void glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN(
			int[] rc, int rc_offset, float[] tc, int tc_offset, float[] c,
			int c_offset, float[] n, int n_offset, float[] v, int v_offset) {
		delegate
				.glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN(rc,
						rc_offset, tc, tc_offset, c, c_offset, n, n_offset, v,
						v_offset);
	}

	public void glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN(
			IntBuffer rc, FloatBuffer tc, FloatBuffer c, FloatBuffer n,
			FloatBuffer v) {
		delegate.glReplacementCodeuiTexCoord2fColor4fNormal3fVertex3fvSUN(rc,
				tc, c, n, v);
	}

	public void glReplacementCodeuiTexCoord2fNormal3fVertex3fSUN(int rc,
			float s, float t, float nx, float ny, float nz, float x, float y,
			float z) {
		delegate.glReplacementCodeuiTexCoord2fNormal3fVertex3fSUN(rc, s, t, nx,
				ny, nz, x, y, z);
	}

	public void glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN(int[] rc,
			int rc_offset, float[] c, int c_offset, float[] n, int n_offset,
			float[] v, int v_offset) {
		delegate.glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN(rc,
				rc_offset, c, c_offset, n, n_offset, v, v_offset);
	}

	public void glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN(IntBuffer rc,
			FloatBuffer c, FloatBuffer n, FloatBuffer v) {
		delegate.glReplacementCodeuiTexCoord2fNormal3fVertex3fvSUN(rc, c, n, v);
	}

	public void glReplacementCodeuiTexCoord2fVertex3fSUN(int rc, float s,
			float t, float x, float y, float z) {
		delegate.glReplacementCodeuiTexCoord2fVertex3fSUN(rc, s, t, x, y, z);
	}

	public void glReplacementCodeuiTexCoord2fVertex3fvSUN(int[] rc,
			int rc_offset, float[] c, int c_offset, float[] v, int v_offset) {
		delegate.glReplacementCodeuiTexCoord2fVertex3fvSUN(rc, rc_offset, c,
				c_offset, v, v_offset);
	}

	public void glReplacementCodeuiTexCoord2fVertex3fvSUN(IntBuffer rc,
			FloatBuffer c, FloatBuffer v) {
		delegate.glReplacementCodeuiTexCoord2fVertex3fvSUN(rc, c, v);
	}

	public void glReplacementCodeuiVertex3fSUN(int target, float s, float t,
			float r) {
		delegate.glReplacementCodeuiVertex3fSUN(target, s, t, r);
	}

	public void glReplacementCodeuiVertex3fvSUN(int[] rc, int rc_offset,
			float[] v, int v_offset) {
		delegate.glReplacementCodeuiVertex3fvSUN(rc, rc_offset, v, v_offset);
	}

	public void glReplacementCodeuiVertex3fvSUN(IntBuffer rc, FloatBuffer v) {
		delegate.glReplacementCodeuiVertex3fvSUN(rc, v);
	}

	public void glRequestResidentProgramsNV(int n, int[] ids, int ids_offset) {
		delegate.glRequestResidentProgramsNV(n, ids, ids_offset);
	}

	public void glRequestResidentProgramsNV(int n, IntBuffer ids) {
		delegate.glRequestResidentProgramsNV(n, ids);
	}

	public void glResetHistogram(int mode) {
		delegate.glResetHistogram(mode);
	}

	public void glResetMinmax(int mode) {
		delegate.glResetMinmax(mode);
	}

	public void glResizeBuffersMESA() {
		delegate.glResizeBuffersMESA();
	}

	public void glRotated(double angle, double x, double y, double z) {
		delegate.glRotated(angle, x, y, z);
	}

	public void glRotatef(float angle, float x, float y, float z) {
		delegate.glRotatef(angle, x, y, z);
	}

	public void glSampleCoverage(float value, boolean invert) {
		delegate.glSampleCoverage(value, invert);
	}

	public void glSampleMapATI(int red, int green, int blue) {
		delegate.glSampleMapATI(red, green, blue);
	}

	public void glSampleMaskEXT(float value, boolean invert) {
		delegate.glSampleMaskEXT(value, invert);
	}

	public void glSampleMaskSGIS(float value, boolean invert) {
		delegate.glSampleMaskSGIS(value, invert);
	}

	public void glSamplePatternEXT(int mode) {
		delegate.glSamplePatternEXT(mode);
	}

	public void glSamplePatternSGIS(int mode) {
		delegate.glSamplePatternSGIS(mode);
	}

	public void glScaled(double x, double y, double z) {
		delegate.glScaled(x, y, z);
	}

	public void glScalef(float x, float y, float z) {
		delegate.glScalef(x, y, z);
	}

	public void glScissor(int x, int y, int width, int height) {
		delegate.glScissor(x, y, width, height);
	}

	public void glSecondaryColor3b(byte red, byte green, byte blue) {
		delegate.glSecondaryColor3b(red, green, blue);
	}

	public void glSecondaryColor3bEXT(byte red, byte green, byte blue) {
		delegate.glSecondaryColor3bEXT(red, green, blue);
	}

	public void glSecondaryColor3bv(byte[] v, int v_offset) {
		delegate.glSecondaryColor3bv(v, v_offset);
	}

	public void glSecondaryColor3bv(ByteBuffer v) {
		delegate.glSecondaryColor3bv(v);
	}

	public void glSecondaryColor3bvEXT(byte[] v, int v_offset) {
		delegate.glSecondaryColor3bvEXT(v, v_offset);
	}

	public void glSecondaryColor3bvEXT(ByteBuffer v) {
		delegate.glSecondaryColor3bvEXT(v);
	}

	public void glSecondaryColor3d(double red, double green, double blue) {
		delegate.glSecondaryColor3d(red, green, blue);
	}

	public void glSecondaryColor3dEXT(double red, double green, double blue) {
		delegate.glSecondaryColor3dEXT(red, green, blue);
	}

	public void glSecondaryColor3dv(double[] m, int m_offset) {
		delegate.glSecondaryColor3dv(m, m_offset);
	}

	public void glSecondaryColor3dv(DoubleBuffer m) {
		delegate.glSecondaryColor3dv(m);
	}

	public void glSecondaryColor3dvEXT(double[] m, int m_offset) {
		delegate.glSecondaryColor3dvEXT(m, m_offset);
	}

	public void glSecondaryColor3dvEXT(DoubleBuffer m) {
		delegate.glSecondaryColor3dvEXT(m);
	}

	public void glSecondaryColor3f(float red, float green, float blue) {
		delegate.glSecondaryColor3f(red, green, blue);
	}

	public void glSecondaryColor3fEXT(float red, float green, float blue) {
		delegate.glSecondaryColor3fEXT(red, green, blue);
	}

	public void glSecondaryColor3fv(float[] m, int m_offset) {
		delegate.glSecondaryColor3fv(m, m_offset);
	}

	public void glSecondaryColor3fv(FloatBuffer m) {
		delegate.glSecondaryColor3fv(m);
	}

	public void glSecondaryColor3fvEXT(float[] m, int m_offset) {
		delegate.glSecondaryColor3fvEXT(m, m_offset);
	}

	public void glSecondaryColor3fvEXT(FloatBuffer m) {
		delegate.glSecondaryColor3fvEXT(m);
	}

	public void glSecondaryColor3hNV(short red, short green, short blue) {
		delegate.glSecondaryColor3hNV(red, green, blue);
	}

	public void glSecondaryColor3hvNV(short[] v, int v_offset) {
		delegate.glSecondaryColor3hvNV(v, v_offset);
	}

	public void glSecondaryColor3hvNV(ShortBuffer v) {
		delegate.glSecondaryColor3hvNV(v);
	}

	public void glSecondaryColor3i(int red, int green, int blue) {
		delegate.glSecondaryColor3i(red, green, blue);
	}

	public void glSecondaryColor3iEXT(int red, int green, int blue) {
		delegate.glSecondaryColor3iEXT(red, green, blue);
	}

	public void glSecondaryColor3iv(int[] v, int v_offset) {
		delegate.glSecondaryColor3iv(v, v_offset);
	}

	public void glSecondaryColor3iv(IntBuffer v) {
		delegate.glSecondaryColor3iv(v);
	}

	public void glSecondaryColor3ivEXT(int[] v, int v_offset) {
		delegate.glSecondaryColor3ivEXT(v, v_offset);
	}

	public void glSecondaryColor3ivEXT(IntBuffer v) {
		delegate.glSecondaryColor3ivEXT(v);
	}

	public void glSecondaryColor3s(short red, short green, short blue) {
		delegate.glSecondaryColor3s(red, green, blue);
	}

	public void glSecondaryColor3sEXT(short red, short green, short blue) {
		delegate.glSecondaryColor3sEXT(red, green, blue);
	}

	public void glSecondaryColor3sv(short[] v, int v_offset) {
		delegate.glSecondaryColor3sv(v, v_offset);
	}

	public void glSecondaryColor3sv(ShortBuffer v) {
		delegate.glSecondaryColor3sv(v);
	}

	public void glSecondaryColor3svEXT(short[] v, int v_offset) {
		delegate.glSecondaryColor3svEXT(v, v_offset);
	}

	public void glSecondaryColor3svEXT(ShortBuffer v) {
		delegate.glSecondaryColor3svEXT(v);
	}

	public void glSecondaryColor3ub(byte red, byte green, byte blue) {
		delegate.glSecondaryColor3ub(red, green, blue);
	}

	public void glSecondaryColor3ubEXT(byte red, byte green, byte blue) {
		delegate.glSecondaryColor3ubEXT(red, green, blue);
	}

	public void glSecondaryColor3ubv(byte[] v, int v_offset) {
		delegate.glSecondaryColor3ubv(v, v_offset);
	}

	public void glSecondaryColor3ubv(ByteBuffer v) {
		delegate.glSecondaryColor3ubv(v);
	}

	public void glSecondaryColor3ubvEXT(byte[] v, int v_offset) {
		delegate.glSecondaryColor3ubvEXT(v, v_offset);
	}

	public void glSecondaryColor3ubvEXT(ByteBuffer v) {
		delegate.glSecondaryColor3ubvEXT(v);
	}

	public void glSecondaryColor3ui(int red, int green, int blue) {
		delegate.glSecondaryColor3ui(red, green, blue);
	}

	public void glSecondaryColor3uiEXT(int red, int green, int blue) {
		delegate.glSecondaryColor3uiEXT(red, green, blue);
	}

	public void glSecondaryColor3uiv(int[] v, int v_offset) {
		delegate.glSecondaryColor3uiv(v, v_offset);
	}

	public void glSecondaryColor3uiv(IntBuffer v) {
		delegate.glSecondaryColor3uiv(v);
	}

	public void glSecondaryColor3uivEXT(int[] v, int v_offset) {
		delegate.glSecondaryColor3uivEXT(v, v_offset);
	}

	public void glSecondaryColor3uivEXT(IntBuffer v) {
		delegate.glSecondaryColor3uivEXT(v);
	}

	public void glSecondaryColor3us(short red, short green, short blue) {
		delegate.glSecondaryColor3us(red, green, blue);
	}

	public void glSecondaryColor3usEXT(short red, short green, short blue) {
		delegate.glSecondaryColor3usEXT(red, green, blue);
	}

	public void glSecondaryColor3usv(short[] v, int v_offset) {
		delegate.glSecondaryColor3usv(v, v_offset);
	}

	public void glSecondaryColor3usv(ShortBuffer v) {
		delegate.glSecondaryColor3usv(v);
	}

	public void glSecondaryColor3usvEXT(short[] v, int v_offset) {
		delegate.glSecondaryColor3usvEXT(v, v_offset);
	}

	public void glSecondaryColor3usvEXT(ShortBuffer v) {
		delegate.glSecondaryColor3usvEXT(v);
	}

	public void glSecondaryColorPointer(int size, int type, int stride,
			Buffer pointer) {
		delegate.glSecondaryColorPointer(size, type, stride, pointer);
	}

	public void glSecondaryColorPointer(int size, int type, int stride,
			long pointer_buffer_offset) {
		delegate.glSecondaryColorPointer(size, type, stride,
				pointer_buffer_offset);
	}

	public void glSecondaryColorPointerEXT(int size, int type, int stride,
			Buffer pointer) {
		delegate.glSecondaryColorPointerEXT(size, type, stride, pointer);
	}

	public void glSecondaryColorPointerEXT(int size, int type, int stride,
			long pointer_buffer_offset) {
		delegate.glSecondaryColorPointerEXT(size, type, stride,
				pointer_buffer_offset);
	}

	public void glSelectBuffer(int size, IntBuffer buffer) {
		delegate.glSelectBuffer(size, buffer);
	}

	public void glSeparableFilter2D(int target, int internalformat, int width,
			int height, int format, int type, Buffer row, Buffer column) {
		delegate.glSeparableFilter2D(target, internalformat, width, height,
				format, type, row, column);
	}

	public void glSeparableFilter2D(int target, int internalformat, int width,
			int height, int format, int type, long row_buffer_offset,
			long column_buffer_offset) {
		delegate.glSeparableFilter2D(target, internalformat, width, height,
				format, type, row_buffer_offset, column_buffer_offset);
	}

	public void glSetFenceAPPLE(int mode) {
		delegate.glSetFenceAPPLE(mode);
	}

	public void glSetFenceNV(int target, int id) {
		delegate.glSetFenceNV(target, id);
	}

	public void glSetFragmentShaderConstantATI(int target, float[] v,
			int v_offset) {
		delegate.glSetFragmentShaderConstantATI(target, v, v_offset);
	}

	public void glSetFragmentShaderConstantATI(int target, FloatBuffer v) {
		delegate.glSetFragmentShaderConstantATI(target, v);
	}

	public void glSetInvariantEXT(int id, int type, Buffer addr) {
		delegate.glSetInvariantEXT(id, type, addr);
	}

	public void glSetLocalConstantEXT(int id, int type, Buffer addr) {
		delegate.glSetLocalConstantEXT(id, type, addr);
	}

	public void glShadeModel(int mode) {
		delegate.glShadeModel(mode);
	}

	public void glShaderOp1EXT(int red, int green, int blue) {
		delegate.glShaderOp1EXT(red, green, blue);
	}

	public void glShaderOp2EXT(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha) {
		delegate.glShaderOp2EXT(sfactorRGB, dfactorRGB, sfactorAlpha,
				dfactorAlpha);
	}

	public void glShaderOp3EXT(int op, int res, int arg1, int arg2, int arg3) {
		delegate.glShaderOp3EXT(op, res, arg1, arg2, arg3);
	}

	public void glShaderSource(int shader, int count, String[] string,
			int[] length, int length_offset) {
		delegate.glShaderSource(shader, count, string, length, length_offset);
	}

	public void glShaderSource(int shader, int count, String[] string,
			IntBuffer length) {
		delegate.glShaderSource(shader, count, string, length);
	}

	public void glShaderSourceARB(int shader, int count, String[] string,
			int[] length, int length_offset) {
		delegate
				.glShaderSourceARB(shader, count, string, length, length_offset);
	}

	public void glShaderSourceARB(int shader, int count, String[] string,
			IntBuffer length) {
		delegate.glShaderSourceARB(shader, count, string, length);
	}

	public void glSharpenTexFuncSGIS(int target, int n, float[] points,
			int points_offset) {
		delegate.glSharpenTexFuncSGIS(target, n, points, points_offset);
	}

	public void glSharpenTexFuncSGIS(int target, int n, FloatBuffer points) {
		delegate.glSharpenTexFuncSGIS(target, n, points);
	}

	public void glSpriteParameterfSGIX(int target, float s) {
		delegate.glSpriteParameterfSGIX(target, s);
	}

	public void glSpriteParameterfvSGIX(int target, float[] v, int v_offset) {
		delegate.glSpriteParameterfvSGIX(target, v, v_offset);
	}

	public void glSpriteParameterfvSGIX(int target, FloatBuffer v) {
		delegate.glSpriteParameterfvSGIX(target, v);
	}

	public void glSpriteParameteriSGIX(int target, int s) {
		delegate.glSpriteParameteriSGIX(target, s);
	}

	public void glSpriteParameterivSGIX(int target, int[] v, int v_offset) {
		delegate.glSpriteParameterivSGIX(target, v, v_offset);
	}

	public void glSpriteParameterivSGIX(int target, IntBuffer v) {
		delegate.glSpriteParameterivSGIX(target, v);
	}

	public void glStartInstrumentsSGIX() {
		delegate.glStartInstrumentsSGIX();
	}

	public void glStencilClearTagEXT(int stencilTagBits, int stencilClearTag) {
		delegate.glStencilClearTagEXT(stencilTagBits, stencilClearTag);
	}

	public void glStencilFunc(int func, int ref, int mask) {
		delegate.glStencilFunc(func, ref, mask);
	}

	public void glStencilFuncSeparate(int frontfunc, int backfunc, int ref,
			int mask) {
		delegate.glStencilFuncSeparate(frontfunc, backfunc, ref, mask);
	}

	public void glStencilFuncSeparateATI(int frontfunc, int backfunc, int ref,
			int mask) {
		delegate.glStencilFuncSeparateATI(frontfunc, backfunc, ref, mask);
	}

	public void glStencilMask(int mask) {
		delegate.glStencilMask(mask);
	}

	public void glStencilMaskSeparate(int target, int id) {
		delegate.glStencilMaskSeparate(target, id);
	}

	public void glStencilOp(int fail, int zfail, int zpass) {
		delegate.glStencilOp(fail, zfail, zpass);
	}

	public void glStencilOpSeparate(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha) {
		delegate.glStencilOpSeparate(sfactorRGB, dfactorRGB, sfactorAlpha,
				dfactorAlpha);
	}

	public void glStencilOpSeparateATI(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha) {
		delegate.glStencilOpSeparateATI(sfactorRGB, dfactorRGB, sfactorAlpha,
				dfactorAlpha);
	}

	public void glStopInstrumentsSGIX(int count) {
		delegate.glStopInstrumentsSGIX(count);
	}

	public void glStringMarkerGREMEDY(int length, Buffer pointer) {
		delegate.glStringMarkerGREMEDY(length, pointer);
	}

	public void glSwapAPPLE() {
		delegate.glSwapAPPLE();
	}

	public void glSwizzleEXT(int stage, int portion, int variable, int input,
			int mapping, int componentUsage) {
		delegate.glSwizzleEXT(stage, portion, variable, input, mapping,
				componentUsage);
	}

	public void glTagSampleBufferSGIX() {
		delegate.glTagSampleBufferSGIX();
	}

	public void glTbufferMask3DFX(int mode) {
		delegate.glTbufferMask3DFX(mode);
	}

	public boolean glTestFenceAPPLE(int id) {
		return delegate.glTestFenceAPPLE(id);
	}

	public boolean glTestFenceNV(int id) {
		return delegate.glTestFenceNV(id);
	}

	public boolean glTestObjectAPPLE(int id, int cap) {
		return delegate.glTestObjectAPPLE(id, cap);
	}

	public void glTexBufferEXT(int target, int internalformat, int buffer) {
		delegate.glTexBufferEXT(target, internalformat, buffer);
	}

	public void glTexBumpParameterfvATI(int target, float[] v, int v_offset) {
		delegate.glTexBumpParameterfvATI(target, v, v_offset);
	}

	public void glTexBumpParameterfvATI(int target, FloatBuffer v) {
		delegate.glTexBumpParameterfvATI(target, v);
	}

	public void glTexBumpParameterivATI(int target, int[] v, int v_offset) {
		delegate.glTexBumpParameterivATI(target, v, v_offset);
	}

	public void glTexBumpParameterivATI(int target, IntBuffer v) {
		delegate.glTexBumpParameterivATI(target, v);
	}

	public void glTexCoord1d(double s) {
		delegate.glTexCoord1d(s);
	}

	public void glTexCoord1dv(double[] v, int v_offset) {
		delegate.glTexCoord1dv(v, v_offset);
	}

	public void glTexCoord1dv(DoubleBuffer v) {
		delegate.glTexCoord1dv(v);
	}

	public void glTexCoord1f(float s) {
		delegate.glTexCoord1f(s);
	}

	public void glTexCoord1fv(float[] v, int v_offset) {
		delegate.glTexCoord1fv(v, v_offset);
	}

	public void glTexCoord1fv(FloatBuffer v) {
		delegate.glTexCoord1fv(v);
	}

	public void glTexCoord1hNV(short factor) {
		delegate.glTexCoord1hNV(factor);
	}

	public void glTexCoord1hvNV(short[] v, int v_offset) {
		delegate.glTexCoord1hvNV(v, v_offset);
	}

	public void glTexCoord1hvNV(ShortBuffer v) {
		delegate.glTexCoord1hvNV(v);
	}

	public void glTexCoord1i(int s) {
		delegate.glTexCoord1i(s);
	}

	public void glTexCoord1iv(int[] v, int v_offset) {
		delegate.glTexCoord1iv(v, v_offset);
	}

	public void glTexCoord1iv(IntBuffer v) {
		delegate.glTexCoord1iv(v);
	}

	public void glTexCoord1s(short s) {
		delegate.glTexCoord1s(s);
	}

	public void glTexCoord1sv(short[] v, int v_offset) {
		delegate.glTexCoord1sv(v, v_offset);
	}

	public void glTexCoord1sv(ShortBuffer v) {
		delegate.glTexCoord1sv(v);
	}

	public void glTexCoord2d(double s, double t) {
		delegate.glTexCoord2d(s, t);
	}

	public void glTexCoord2dv(double[] v, int v_offset) {
		delegate.glTexCoord2dv(v, v_offset);
	}

	public void glTexCoord2dv(DoubleBuffer v) {
		delegate.glTexCoord2dv(v);
	}

	public void glTexCoord2f(float s, float t) {
		delegate.glTexCoord2f(s, t);
	}

	public void glTexCoord2fColor3fVertex3fSUN(float s, float t, float p,
			float q, float x, float y, float z, float w) {
		delegate.glTexCoord2fColor3fVertex3fSUN(s, t, p, q, x, y, z, w);
	}

	public void glTexCoord2fColor3fVertex3fvSUN(float[] c, int c_offset,
			float[] n, int n_offset, float[] v, int v_offset) {
		delegate.glTexCoord2fColor3fVertex3fvSUN(c, c_offset, n, n_offset, v,
				v_offset);
	}

	public void glTexCoord2fColor3fVertex3fvSUN(FloatBuffer c, FloatBuffer n,
			FloatBuffer v) {
		delegate.glTexCoord2fColor3fVertex3fvSUN(c, n, v);
	}

	public void glTexCoord2fColor4fNormal3fVertex3fSUN(float s, float t,
			float r, float g, float b, float a, float nx, float ny, float nz,
			float x, float y, float z) {
		delegate.glTexCoord2fColor4fNormal3fVertex3fSUN(s, t, r, g, b, a, nx,
				ny, nz, x, y, z);
	}

	public void glTexCoord2fColor4fNormal3fVertex3fvSUN(float[] tc,
			int tc_offset, float[] c, int c_offset, float[] n, int n_offset,
			float[] v, int v_offset) {
		delegate.glTexCoord2fColor4fNormal3fVertex3fvSUN(tc, tc_offset, c,
				c_offset, n, n_offset, v, v_offset);
	}

	public void glTexCoord2fColor4fNormal3fVertex3fvSUN(FloatBuffer tc,
			FloatBuffer c, FloatBuffer n, FloatBuffer v) {
		delegate.glTexCoord2fColor4fNormal3fVertex3fvSUN(tc, c, n, v);
	}

	public void glTexCoord2fColor4ubVertex3fSUN(float s, float t, byte r,
			byte g, byte b, byte a, float x, float y, float z) {
		delegate.glTexCoord2fColor4ubVertex3fSUN(s, t, r, g, b, a, x, y, z);
	}

	public void glTexCoord2fColor4ubVertex3fvSUN(float[] tc, int tc_offset,
			byte[] c, int c_offset, float[] v, int v_offset) {
		delegate.glTexCoord2fColor4ubVertex3fvSUN(tc, tc_offset, c, c_offset,
				v, v_offset);
	}

	public void glTexCoord2fColor4ubVertex3fvSUN(FloatBuffer tc, ByteBuffer c,
			FloatBuffer v) {
		delegate.glTexCoord2fColor4ubVertex3fvSUN(tc, c, v);
	}

	public void glTexCoord2fNormal3fVertex3fSUN(float s, float t, float p,
			float q, float x, float y, float z, float w) {
		delegate.glTexCoord2fNormal3fVertex3fSUN(s, t, p, q, x, y, z, w);
	}

	public void glTexCoord2fNormal3fVertex3fvSUN(float[] c, int c_offset,
			float[] n, int n_offset, float[] v, int v_offset) {
		delegate.glTexCoord2fNormal3fVertex3fvSUN(c, c_offset, n, n_offset, v,
				v_offset);
	}

	public void glTexCoord2fNormal3fVertex3fvSUN(FloatBuffer c, FloatBuffer n,
			FloatBuffer v) {
		delegate.glTexCoord2fNormal3fVertex3fvSUN(c, n, v);
	}

	public void glTexCoord2fv(float[] v, int v_offset) {
		delegate.glTexCoord2fv(v, v_offset);
	}

	public void glTexCoord2fv(FloatBuffer v) {
		delegate.glTexCoord2fv(v);
	}

	public void glTexCoord2fVertex3fSUN(float s, float t, float x, float y,
			float z) {
		delegate.glTexCoord2fVertex3fSUN(s, t, x, y, z);
	}

	public void glTexCoord2fVertex3fvSUN(float[] c, int c_offset, float[] v,
			int v_offset) {
		delegate.glTexCoord2fVertex3fvSUN(c, c_offset, v, v_offset);
	}

	public void glTexCoord2fVertex3fvSUN(FloatBuffer c, FloatBuffer v) {
		delegate.glTexCoord2fVertex3fvSUN(c, v);
	}

	public void glTexCoord2hNV(short x, short y) {
		delegate.glTexCoord2hNV(x, y);
	}

	public void glTexCoord2hvNV(short[] v, int v_offset) {
		delegate.glTexCoord2hvNV(v, v_offset);
	}

	public void glTexCoord2hvNV(ShortBuffer v) {
		delegate.glTexCoord2hvNV(v);
	}

	public void glTexCoord2i(int s, int t) {
		delegate.glTexCoord2i(s, t);
	}

	public void glTexCoord2iv(int[] v, int v_offset) {
		delegate.glTexCoord2iv(v, v_offset);
	}

	public void glTexCoord2iv(IntBuffer v) {
		delegate.glTexCoord2iv(v);
	}

	public void glTexCoord2s(short s, short t) {
		delegate.glTexCoord2s(s, t);
	}

	public void glTexCoord2sv(short[] v, int v_offset) {
		delegate.glTexCoord2sv(v, v_offset);
	}

	public void glTexCoord2sv(ShortBuffer v) {
		delegate.glTexCoord2sv(v);
	}

	public void glTexCoord3d(double s, double t, double r) {
		delegate.glTexCoord3d(s, t, r);
	}

	public void glTexCoord3dv(double[] v, int v_offset) {
		delegate.glTexCoord3dv(v, v_offset);
	}

	public void glTexCoord3dv(DoubleBuffer v) {
		delegate.glTexCoord3dv(v);
	}

	public void glTexCoord3f(float s, float t, float r) {
		delegate.glTexCoord3f(s, t, r);
	}

	public void glTexCoord3fv(float[] v, int v_offset) {
		delegate.glTexCoord3fv(v, v_offset);
	}

	public void glTexCoord3fv(FloatBuffer v) {
		delegate.glTexCoord3fv(v);
	}

	public void glTexCoord3hNV(short red, short green, short blue) {
		delegate.glTexCoord3hNV(red, green, blue);
	}

	public void glTexCoord3hvNV(short[] v, int v_offset) {
		delegate.glTexCoord3hvNV(v, v_offset);
	}

	public void glTexCoord3hvNV(ShortBuffer v) {
		delegate.glTexCoord3hvNV(v);
	}

	public void glTexCoord3i(int s, int t, int r) {
		delegate.glTexCoord3i(s, t, r);
	}

	public void glTexCoord3iv(int[] v, int v_offset) {
		delegate.glTexCoord3iv(v, v_offset);
	}

	public void glTexCoord3iv(IntBuffer v) {
		delegate.glTexCoord3iv(v);
	}

	public void glTexCoord3s(short s, short t, short r) {
		delegate.glTexCoord3s(s, t, r);
	}

	public void glTexCoord3sv(short[] v, int v_offset) {
		delegate.glTexCoord3sv(v, v_offset);
	}

	public void glTexCoord3sv(ShortBuffer v) {
		delegate.glTexCoord3sv(v);
	}

	public void glTexCoord4d(double s, double t, double r, double q) {
		delegate.glTexCoord4d(s, t, r, q);
	}

	public void glTexCoord4dv(double[] v, int v_offset) {
		delegate.glTexCoord4dv(v, v_offset);
	}

	public void glTexCoord4dv(DoubleBuffer v) {
		delegate.glTexCoord4dv(v);
	}

	public void glTexCoord4f(float s, float t, float r, float q) {
		delegate.glTexCoord4f(s, t, r, q);
	}

	public void glTexCoord4fColor4fNormal3fVertex4fSUN(float s, float t,
			float p, float q, float r, float g, float b, float a, float nx,
			float ny, float nz, float x, float y, float z, float w) {
		delegate.glTexCoord4fColor4fNormal3fVertex4fSUN(s, t, p, q, r, g, b, a,
				nx, ny, nz, x, y, z, w);
	}

	public void glTexCoord4fColor4fNormal3fVertex4fvSUN(float[] tc,
			int tc_offset, float[] c, int c_offset, float[] n, int n_offset,
			float[] v, int v_offset) {
		delegate.glTexCoord4fColor4fNormal3fVertex4fvSUN(tc, tc_offset, c,
				c_offset, n, n_offset, v, v_offset);
	}

	public void glTexCoord4fColor4fNormal3fVertex4fvSUN(FloatBuffer tc,
			FloatBuffer c, FloatBuffer n, FloatBuffer v) {
		delegate.glTexCoord4fColor4fNormal3fVertex4fvSUN(tc, c, n, v);
	}

	public void glTexCoord4fv(float[] v, int v_offset) {
		delegate.glTexCoord4fv(v, v_offset);
	}

	public void glTexCoord4fv(FloatBuffer v) {
		delegate.glTexCoord4fv(v);
	}

	public void glTexCoord4fVertex4fSUN(float s, float t, float p, float q,
			float x, float y, float z, float w) {
		delegate.glTexCoord4fVertex4fSUN(s, t, p, q, x, y, z, w);
	}

	public void glTexCoord4fVertex4fvSUN(float[] c, int c_offset, float[] v,
			int v_offset) {
		delegate.glTexCoord4fVertex4fvSUN(c, c_offset, v, v_offset);
	}

	public void glTexCoord4fVertex4fvSUN(FloatBuffer c, FloatBuffer v) {
		delegate.glTexCoord4fVertex4fvSUN(c, v);
	}

	public void glTexCoord4hNV(short x, short y, short z, short w) {
		delegate.glTexCoord4hNV(x, y, z, w);
	}

	public void glTexCoord4hvNV(short[] v, int v_offset) {
		delegate.glTexCoord4hvNV(v, v_offset);
	}

	public void glTexCoord4hvNV(ShortBuffer v) {
		delegate.glTexCoord4hvNV(v);
	}

	public void glTexCoord4i(int s, int t, int r, int q) {
		delegate.glTexCoord4i(s, t, r, q);
	}

	public void glTexCoord4iv(int[] v, int v_offset) {
		delegate.glTexCoord4iv(v, v_offset);
	}

	public void glTexCoord4iv(IntBuffer v) {
		delegate.glTexCoord4iv(v);
	}

	public void glTexCoord4s(short s, short t, short r, short q) {
		delegate.glTexCoord4s(s, t, r, q);
	}

	public void glTexCoord4sv(short[] v, int v_offset) {
		delegate.glTexCoord4sv(v, v_offset);
	}

	public void glTexCoord4sv(ShortBuffer v) {
		delegate.glTexCoord4sv(v);
	}

	public void glTexCoordPointer(int size, int type, int stride, Buffer ptr) {
		delegate.glTexCoordPointer(size, type, stride, ptr);
	}

	public void glTexCoordPointer(int size, int type, int stride,
			long ptr_buffer_offset) {
		delegate.glTexCoordPointer(size, type, stride, ptr_buffer_offset);
	}

	public void glTexEnvf(int target, int pname, float param) {
		delegate.glTexEnvf(target, pname, param);
	}

	public void glTexEnvfv(int target, int pname, float[] params,
			int params_offset) {
		delegate.glTexEnvfv(target, pname, params, params_offset);
	}

	public void glTexEnvfv(int target, int pname, FloatBuffer params) {
		delegate.glTexEnvfv(target, pname, params);
	}

	public void glTexEnvi(int target, int pname, int param) {
		delegate.glTexEnvi(target, pname, param);
	}

	public void glTexEnviv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glTexEnviv(target, pname, params, params_offset);
	}

	public void glTexEnviv(int target, int pname, IntBuffer params) {
		delegate.glTexEnviv(target, pname, params);
	}

	public void glTexFilterFuncSGIS(int target, int filter, int n,
			float[] weights, int weights_offset) {
		delegate
				.glTexFilterFuncSGIS(target, filter, n, weights, weights_offset);
	}

	public void glTexFilterFuncSGIS(int target, int filter, int n,
			FloatBuffer weights) {
		delegate.glTexFilterFuncSGIS(target, filter, n, weights);
	}

	public void glTexGend(int coord, int pname, double param) {
		delegate.glTexGend(coord, pname, param);
	}

	public void glTexGendv(int coord, int pname, double[] params,
			int params_offset) {
		delegate.glTexGendv(coord, pname, params, params_offset);
	}

	public void glTexGendv(int coord, int pname, DoubleBuffer params) {
		delegate.glTexGendv(coord, pname, params);
	}

	public void glTexGenf(int coord, int pname, float param) {
		delegate.glTexGenf(coord, pname, param);
	}

	public void glTexGenfv(int coord, int pname, float[] params,
			int params_offset) {
		delegate.glTexGenfv(coord, pname, params, params_offset);
	}

	public void glTexGenfv(int coord, int pname, FloatBuffer params) {
		delegate.glTexGenfv(coord, pname, params);
	}

	public void glTexGeni(int coord, int pname, int param) {
		delegate.glTexGeni(coord, pname, param);
	}

	public void glTexGeniv(int coord, int pname, int[] params, int params_offset) {
		delegate.glTexGeniv(coord, pname, params, params_offset);
	}

	public void glTexGeniv(int coord, int pname, IntBuffer params) {
		delegate.glTexGeniv(coord, pname, params);
	}

	public void glTexImage1D(int target, int level, int internalFormat,
			int width, int border, int format, int type, Buffer pixels) {
		delegate.glTexImage1D(target, level, internalFormat, width, border,
				format, type, pixels);
	}

	public void glTexImage1D(int target, int level, int internalFormat,
			int width, int border, int format, int type,
			long pixels_buffer_offset) {
		delegate.glTexImage1D(target, level, internalFormat, width, border,
				format, type, pixels_buffer_offset);
	}

	public void glTexImage2D(int target, int level, int internalFormat,
			int width, int height, int border, int format, int type,
			Buffer pixels) {
		delegate.glTexImage2D(target, level, internalFormat, width, height,
				border, format, type, pixels);
	}

	public void glTexImage2D(int target, int level, int internalFormat,
			int width, int height, int border, int format, int type,
			long pixels_buffer_offset) {
		delegate.glTexImage2D(target, level, internalFormat, width, height,
				border, format, type, pixels_buffer_offset);
	}

	public void glTexImage3D(int target, int level, int internalformat,
			int width, int height, int depth, int border, int format, int type,
			Buffer pixels) {
		delegate.glTexImage3D(target, level, internalformat, width, height,
				depth, border, format, type, pixels);
	}

	public void glTexImage3D(int target, int level, int internalformat,
			int width, int height, int depth, int border, int format, int type,
			long pixels_buffer_offset) {
		delegate.glTexImage3D(target, level, internalformat, width, height,
				depth, border, format, type, pixels_buffer_offset);
	}

	public void glTexImage4DSGIS(int target, int level, int internalformat,
			int width, int height, int depth, int size4d, int border,
			int format, int type, Buffer pixels) {
		delegate.glTexImage4DSGIS(target, level, internalformat, width, height,
				depth, size4d, border, format, type, pixels);
	}

	public void glTexParameterf(int target, int pname, float param) {
		delegate.glTexParameterf(target, pname, param);
	}

	public void glTexParameterfv(int target, int pname, float[] params,
			int params_offset) {
		delegate.glTexParameterfv(target, pname, params, params_offset);
	}

	public void glTexParameterfv(int target, int pname, FloatBuffer params) {
		delegate.glTexParameterfv(target, pname, params);
	}

	public void glTexParameteri(int target, int pname, int param) {
		delegate.glTexParameteri(target, pname, param);
	}

	public void glTexParameterIivEXT(int target, int pname, int[] params,
			int params_offset) {
		delegate.glTexParameterIivEXT(target, pname, params, params_offset);
	}

	public void glTexParameterIivEXT(int target, int pname, IntBuffer params) {
		delegate.glTexParameterIivEXT(target, pname, params);
	}

	public void glTexParameterIuivEXT(int target, int pname, int[] params,
			int params_offset) {
		delegate.glTexParameterIuivEXT(target, pname, params, params_offset);
	}

	public void glTexParameterIuivEXT(int target, int pname, IntBuffer params) {
		delegate.glTexParameterIuivEXT(target, pname, params);
	}

	public void glTexParameteriv(int target, int pname, int[] params,
			int params_offset) {
		delegate.glTexParameteriv(target, pname, params, params_offset);
	}

	public void glTexParameteriv(int target, int pname, IntBuffer params) {
		delegate.glTexParameteriv(target, pname, params);
	}

	public void glTexSubImage1D(int target, int level, int xoffset, int width,
			int format, int type, Buffer pixels) {
		delegate.glTexSubImage1D(target, level, xoffset, width, format, type,
				pixels);
	}

	public void glTexSubImage1D(int target, int level, int xoffset, int width,
			int format, int type, long pixels_buffer_offset) {
		delegate.glTexSubImage1D(target, level, xoffset, width, format, type,
				pixels_buffer_offset);
	}

	public void glTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int type,
			Buffer pixels) {
		delegate.glTexSubImage2D(target, level, xoffset, yoffset, width,
				height, format, type, pixels);
	}

	public void glTexSubImage2D(int target, int level, int xoffset,
			int yoffset, int width, int height, int format, int type,
			long pixels_buffer_offset) {
		delegate.glTexSubImage2D(target, level, xoffset, yoffset, width,
				height, format, type, pixels_buffer_offset);
	}

	public void glTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int type, Buffer pixels) {
		delegate.glTexSubImage3D(target, level, xoffset, yoffset, zoffset,
				width, height, depth, format, type, pixels);
	}

	public void glTexSubImage3D(int target, int level, int xoffset,
			int yoffset, int zoffset, int width, int height, int depth,
			int format, int type, long pixels_buffer_offset) {
		delegate.glTexSubImage3D(target, level, xoffset, yoffset, zoffset,
				width, height, depth, format, type, pixels_buffer_offset);
	}

	public void glTexSubImage4DSGIS(int target, int level, int xoffset,
			int yoffset, int zoffset, int woffset, int width, int height,
			int depth, int size4d, int format, int type, Buffer pixels) {
		delegate.glTexSubImage4DSGIS(target, level, xoffset, yoffset, zoffset,
				woffset, width, height, depth, size4d, format, type, pixels);
	}

	public void glTextureColorMaskSGIS(boolean red, boolean green,
			boolean blue, boolean alpha) {
		delegate.glTextureColorMaskSGIS(red, green, blue, alpha);
	}

	public void glTextureLightEXT(int mode) {
		delegate.glTextureLightEXT(mode);
	}

	public void glTextureMaterialEXT(int target, int id) {
		delegate.glTextureMaterialEXT(target, id);
	}

	public void glTextureNormalEXT(int mode) {
		delegate.glTextureNormalEXT(mode);
	}

	public void glTextureRangeAPPLE(int target, int length, Buffer pointer) {
		delegate.glTextureRangeAPPLE(target, length, pointer);
	}

	public void glTrackMatrixNV(int sfactorRGB, int dfactorRGB,
			int sfactorAlpha, int dfactorAlpha) {
		delegate.glTrackMatrixNV(sfactorRGB, dfactorRGB, sfactorAlpha,
				dfactorAlpha);
	}

	public void glTransformFeedbackAttribsNV(int count, int[] attribs,
			int attribs_offset, int bufferMode) {
		delegate.glTransformFeedbackAttribsNV(count, attribs, attribs_offset,
				bufferMode);
	}

	public void glTransformFeedbackAttribsNV(int count, IntBuffer attribs,
			int bufferMode) {
		delegate.glTransformFeedbackAttribsNV(count, attribs, bufferMode);
	}

	public void glTransformFeedbackVaryingsNV(int program, int count,
			int[] locations, int locations_offset, int bufferMode) {
		delegate.glTransformFeedbackVaryingsNV(program, count, locations,
				locations_offset, bufferMode);
	}

	public void glTransformFeedbackVaryingsNV(int program, int count,
			IntBuffer locations, int bufferMode) {
		delegate.glTransformFeedbackVaryingsNV(program, count, locations,
				bufferMode);
	}

	public void glTranslated(double x, double y, double z) {
		delegate.glTranslated(x, y, z);
	}

	public void glTranslatef(float x, float y, float z) {
		delegate.glTranslatef(x, y, z);
	}

	public void glUniform1f(int location, float v0) {
		delegate.glUniform1f(location, v0);
	}

	public void glUniform1fARB(int location, float v0) {
		delegate.glUniform1fARB(location, v0);
	}

	public void glUniform1fv(int location, int count, float[] value,
			int value_offset) {
		delegate.glUniform1fv(location, count, value, value_offset);
	}

	public void glUniform1fv(int location, int count, FloatBuffer value) {
		delegate.glUniform1fv(location, count, value);
	}

	public void glUniform1fvARB(int location, int count, float[] value,
			int value_offset) {
		delegate.glUniform1fvARB(location, count, value, value_offset);
	}

	public void glUniform1fvARB(int location, int count, FloatBuffer value) {
		delegate.glUniform1fvARB(location, count, value);
	}

	public void glUniform1i(int x, int y) {
		delegate.glUniform1i(x, y);
	}

	public void glUniform1iARB(int x, int y) {
		delegate.glUniform1iARB(x, y);
	}

	public void glUniform1iv(int location, int count, int[] value,
			int value_offset) {
		delegate.glUniform1iv(location, count, value, value_offset);
	}

	public void glUniform1iv(int location, int count, IntBuffer value) {
		delegate.glUniform1iv(location, count, value);
	}

	public void glUniform1ivARB(int location, int count, int[] value,
			int value_offset) {
		delegate.glUniform1ivARB(location, count, value, value_offset);
	}

	public void glUniform1ivARB(int location, int count, IntBuffer value) {
		delegate.glUniform1ivARB(location, count, value);
	}

	public void glUniform1uiEXT(int location, int v0) {
		delegate.glUniform1uiEXT(location, v0);
	}

	public void glUniform1uivEXT(int location, int count, int[] value,
			int value_offset) {
		delegate.glUniform1uivEXT(location, count, value, value_offset);
	}

	public void glUniform1uivEXT(int location, int count, IntBuffer value) {
		delegate.glUniform1uivEXT(location, count, value);
	}

	public void glUniform2f(int location, float v0, float v1) {
		delegate.glUniform2f(location, v0, v1);
	}

	public void glUniform2fARB(int location, float v0, float v1) {
		delegate.glUniform2fARB(location, v0, v1);
	}

	public void glUniform2fv(int location, int count, float[] value,
			int value_offset) {
		delegate.glUniform2fv(location, count, value, value_offset);
	}

	public void glUniform2fv(int location, int count, FloatBuffer value) {
		delegate.glUniform2fv(location, count, value);
	}

	public void glUniform2fvARB(int location, int count, float[] value,
			int value_offset) {
		delegate.glUniform2fvARB(location, count, value, value_offset);
	}

	public void glUniform2fvARB(int location, int count, FloatBuffer value) {
		delegate.glUniform2fvARB(location, count, value);
	}

	public void glUniform2i(int red, int green, int blue) {
		delegate.glUniform2i(red, green, blue);
	}

	public void glUniform2iARB(int red, int green, int blue) {
		delegate.glUniform2iARB(red, green, blue);
	}

	public void glUniform2iv(int location, int count, int[] value,
			int value_offset) {
		delegate.glUniform2iv(location, count, value, value_offset);
	}

	public void glUniform2iv(int location, int count, IntBuffer value) {
		delegate.glUniform2iv(location, count, value);
	}

	public void glUniform2ivARB(int location, int count, int[] value,
			int value_offset) {
		delegate.glUniform2ivARB(location, count, value, value_offset);
	}

	public void glUniform2ivARB(int location, int count, IntBuffer value) {
		delegate.glUniform2ivARB(location, count, value);
	}

	public void glUniform2uiEXT(int location, int v0, int v1) {
		delegate.glUniform2uiEXT(location, v0, v1);
	}

	public void glUniform2uivEXT(int location, int count, int[] value,
			int value_offset) {
		delegate.glUniform2uivEXT(location, count, value, value_offset);
	}

	public void glUniform2uivEXT(int location, int count, IntBuffer value) {
		delegate.glUniform2uivEXT(location, count, value);
	}

	public void glUniform3f(int location, float v0, float v1, float v2) {
		delegate.glUniform3f(location, v0, v1, v2);
	}

	public void glUniform3fARB(int location, float v0, float v1, float v2) {
		delegate.glUniform3fARB(location, v0, v1, v2);
	}

	public void glUniform3fv(int location, int count, float[] value,
			int value_offset) {
		delegate.glUniform3fv(location, count, value, value_offset);
	}

	public void glUniform3fv(int location, int count, FloatBuffer value) {
		delegate.glUniform3fv(location, count, value);
	}

	public void glUniform3fvARB(int location, int count, float[] value,
			int value_offset) {
		delegate.glUniform3fvARB(location, count, value, value_offset);
	}

	public void glUniform3fvARB(int location, int count, FloatBuffer value) {
		delegate.glUniform3fvARB(location, count, value);
	}

	public void glUniform3i(int location, int v0, int v1, int v2) {
		delegate.glUniform3i(location, v0, v1, v2);
	}

	public void glUniform3iARB(int location, int v0, int v1, int v2) {
		delegate.glUniform3iARB(location, v0, v1, v2);
	}

	public void glUniform3iv(int location, int count, int[] value,
			int value_offset) {
		delegate.glUniform3iv(location, count, value, value_offset);
	}

	public void glUniform3iv(int location, int count, IntBuffer value) {
		delegate.glUniform3iv(location, count, value);
	}

	public void glUniform3ivARB(int location, int count, int[] value,
			int value_offset) {
		delegate.glUniform3ivARB(location, count, value, value_offset);
	}

	public void glUniform3ivARB(int location, int count, IntBuffer value) {
		delegate.glUniform3ivARB(location, count, value);
	}

	public void glUniform3uiEXT(int location, int v0, int v1, int v2) {
		delegate.glUniform3uiEXT(location, v0, v1, v2);
	}

	public void glUniform3uivEXT(int location, int count, int[] value,
			int value_offset) {
		delegate.glUniform3uivEXT(location, count, value, value_offset);
	}

	public void glUniform3uivEXT(int location, int count, IntBuffer value) {
		delegate.glUniform3uivEXT(location, count, value);
	}

	public void glUniform4f(int location, float v0, float v1, float v2, float v3) {
		delegate.glUniform4f(location, v0, v1, v2, v3);
	}

	public void glUniform4fARB(int location, float v0, float v1, float v2,
			float v3) {
		delegate.glUniform4fARB(location, v0, v1, v2, v3);
	}

	public void glUniform4fv(int location, int count, float[] value,
			int value_offset) {
		delegate.glUniform4fv(location, count, value, value_offset);
	}

	public void glUniform4fv(int location, int count, FloatBuffer value) {
		delegate.glUniform4fv(location, count, value);
	}

	public void glUniform4fvARB(int location, int count, float[] value,
			int value_offset) {
		delegate.glUniform4fvARB(location, count, value, value_offset);
	}

	public void glUniform4fvARB(int location, int count, FloatBuffer value) {
		delegate.glUniform4fvARB(location, count, value);
	}

	public void glUniform4i(int location, int v0, int v1, int v2, int v3) {
		delegate.glUniform4i(location, v0, v1, v2, v3);
	}

	public void glUniform4iARB(int location, int v0, int v1, int v2, int v3) {
		delegate.glUniform4iARB(location, v0, v1, v2, v3);
	}

	public void glUniform4iv(int location, int count, int[] value,
			int value_offset) {
		delegate.glUniform4iv(location, count, value, value_offset);
	}

	public void glUniform4iv(int location, int count, IntBuffer value) {
		delegate.glUniform4iv(location, count, value);
	}

	public void glUniform4ivARB(int location, int count, int[] value,
			int value_offset) {
		delegate.glUniform4ivARB(location, count, value, value_offset);
	}

	public void glUniform4ivARB(int location, int count, IntBuffer value) {
		delegate.glUniform4ivARB(location, count, value);
	}

	public void glUniform4uiEXT(int location, int v0, int v1, int v2, int v3) {
		delegate.glUniform4uiEXT(location, v0, v1, v2, v3);
	}

	public void glUniform4uivEXT(int location, int count, int[] value,
			int value_offset) {
		delegate.glUniform4uivEXT(location, count, value, value_offset);
	}

	public void glUniform4uivEXT(int location, int count, IntBuffer value) {
		delegate.glUniform4uivEXT(location, count, value);
	}

	public void glUniformBufferEXT(int program, int location, int buffer) {
		delegate.glUniformBufferEXT(program, location, buffer);
	}

	public void glUniformMatrix2fv(int location, int count, boolean transpose,
			float[] value, int value_offset) {
		delegate.glUniformMatrix2fv(location, count, transpose, value,
				value_offset);
	}

	public void glUniformMatrix2fv(int location, int count, boolean transpose,
			FloatBuffer value) {
		delegate.glUniformMatrix2fv(location, count, transpose, value);
	}

	public void glUniformMatrix2fvARB(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		delegate.glUniformMatrix2fvARB(location, count, transpose, value,
				value_offset);
	}

	public void glUniformMatrix2fvARB(int location, int count,
			boolean transpose, FloatBuffer value) {
		delegate.glUniformMatrix2fvARB(location, count, transpose, value);
	}

	public void glUniformMatrix2x3fv(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		delegate.glUniformMatrix2x3fv(location, count, transpose, value,
				value_offset);
	}

	public void glUniformMatrix2x3fv(int location, int count,
			boolean transpose, FloatBuffer value) {
		delegate.glUniformMatrix2x3fv(location, count, transpose, value);
	}

	public void glUniformMatrix2x4fv(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		delegate.glUniformMatrix2x4fv(location, count, transpose, value,
				value_offset);
	}

	public void glUniformMatrix2x4fv(int location, int count,
			boolean transpose, FloatBuffer value) {
		delegate.glUniformMatrix2x4fv(location, count, transpose, value);
	}

	public void glUniformMatrix3fv(int location, int count, boolean transpose,
			float[] value, int value_offset) {
		delegate.glUniformMatrix3fv(location, count, transpose, value,
				value_offset);
	}

	public void glUniformMatrix3fv(int location, int count, boolean transpose,
			FloatBuffer value) {
		delegate.glUniformMatrix3fv(location, count, transpose, value);
	}

	public void glUniformMatrix3fvARB(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		delegate.glUniformMatrix3fvARB(location, count, transpose, value,
				value_offset);
	}

	public void glUniformMatrix3fvARB(int location, int count,
			boolean transpose, FloatBuffer value) {
		delegate.glUniformMatrix3fvARB(location, count, transpose, value);
	}

	public void glUniformMatrix3x2fv(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		delegate.glUniformMatrix3x2fv(location, count, transpose, value,
				value_offset);
	}

	public void glUniformMatrix3x2fv(int location, int count,
			boolean transpose, FloatBuffer value) {
		delegate.glUniformMatrix3x2fv(location, count, transpose, value);
	}

	public void glUniformMatrix3x4fv(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		delegate.glUniformMatrix3x4fv(location, count, transpose, value,
				value_offset);
	}

	public void glUniformMatrix3x4fv(int location, int count,
			boolean transpose, FloatBuffer value) {
		delegate.glUniformMatrix3x4fv(location, count, transpose, value);
	}

	public void glUniformMatrix4fv(int location, int count, boolean transpose,
			float[] value, int value_offset) {
		delegate.glUniformMatrix4fv(location, count, transpose, value,
				value_offset);
	}

	public void glUniformMatrix4fv(int location, int count, boolean transpose,
			FloatBuffer value) {
		delegate.glUniformMatrix4fv(location, count, transpose, value);
	}

	public void glUniformMatrix4fvARB(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		delegate.glUniformMatrix4fvARB(location, count, transpose, value,
				value_offset);
	}

	public void glUniformMatrix4fvARB(int location, int count,
			boolean transpose, FloatBuffer value) {
		delegate.glUniformMatrix4fvARB(location, count, transpose, value);
	}

	public void glUniformMatrix4x2fv(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		delegate.glUniformMatrix4x2fv(location, count, transpose, value,
				value_offset);
	}

	public void glUniformMatrix4x2fv(int location, int count,
			boolean transpose, FloatBuffer value) {
		delegate.glUniformMatrix4x2fv(location, count, transpose, value);
	}

	public void glUniformMatrix4x3fv(int location, int count,
			boolean transpose, float[] value, int value_offset) {
		delegate.glUniformMatrix4x3fv(location, count, transpose, value,
				value_offset);
	}

	public void glUniformMatrix4x3fv(int location, int count,
			boolean transpose, FloatBuffer value) {
		delegate.glUniformMatrix4x3fv(location, count, transpose, value);
	}

	public void glUnlockArraysEXT() {
		delegate.glUnlockArraysEXT();
	}

	public boolean glUnmapBuffer(int id) {
		return delegate.glUnmapBuffer(id);
	}

	public boolean glUnmapBufferARB(int id) {
		return delegate.glUnmapBufferARB(id);
	}

	public void glUpdateObjectBufferATI(int buffer, int offset, int size,
			Buffer pointer, int preserve) {
		delegate.glUpdateObjectBufferATI(buffer, offset, size, pointer,
				preserve);
	}

	public void glUseProgram(int mode) {
		delegate.glUseProgram(mode);
	}

	public void glUseProgramObjectARB(int mode) {
		delegate.glUseProgramObjectARB(mode);
	}

	public void glValidateProgram(int mode) {
		delegate.glValidateProgram(mode);
	}

	public void glValidateProgramARB(int mode) {
		delegate.glValidateProgramARB(mode);
	}

	public void glVariantArrayObjectATI(int id, int type, int stride,
			int buffer, int offset) {
		delegate.glVariantArrayObjectATI(id, type, stride, buffer, offset);
	}

	public void glVariantbvEXT(int index, byte[] v, int v_offset) {
		delegate.glVariantbvEXT(index, v, v_offset);
	}

	public void glVariantbvEXT(int index, ByteBuffer v) {
		delegate.glVariantbvEXT(index, v);
	}

	public void glVariantdvEXT(int target, double[] v, int v_offset) {
		delegate.glVariantdvEXT(target, v, v_offset);
	}

	public void glVariantdvEXT(int target, DoubleBuffer v) {
		delegate.glVariantdvEXT(target, v);
	}

	public void glVariantfvEXT(int target, float[] v, int v_offset) {
		delegate.glVariantfvEXT(target, v, v_offset);
	}

	public void glVariantfvEXT(int target, FloatBuffer v) {
		delegate.glVariantfvEXT(target, v);
	}

	public void glVariantivEXT(int target, int[] v, int v_offset) {
		delegate.glVariantivEXT(target, v, v_offset);
	}

	public void glVariantivEXT(int target, IntBuffer v) {
		delegate.glVariantivEXT(target, v);
	}

	public void glVariantPointerEXT(int id, int type, int stride, Buffer addr) {
		delegate.glVariantPointerEXT(id, type, stride, addr);
	}

	public void glVariantPointerEXT(int id, int type, int stride,
			long addr_buffer_offset) {
		delegate.glVariantPointerEXT(id, type, stride, addr_buffer_offset);
	}

	public void glVariantsvEXT(int target, short[] v, int v_offset) {
		delegate.glVariantsvEXT(target, v, v_offset);
	}

	public void glVariantsvEXT(int target, ShortBuffer v) {
		delegate.glVariantsvEXT(target, v);
	}

	public void glVariantubvEXT(int index, byte[] v, int v_offset) {
		delegate.glVariantubvEXT(index, v, v_offset);
	}

	public void glVariantubvEXT(int index, ByteBuffer v) {
		delegate.glVariantubvEXT(index, v);
	}

	public void glVariantuivEXT(int index, int[] v, int v_offset) {
		delegate.glVariantuivEXT(index, v, v_offset);
	}

	public void glVariantuivEXT(int index, IntBuffer v) {
		delegate.glVariantuivEXT(index, v);
	}

	public void glVariantusvEXT(int index, short[] v, int v_offset) {
		delegate.glVariantusvEXT(index, v, v_offset);
	}

	public void glVariantusvEXT(int index, ShortBuffer v) {
		delegate.glVariantusvEXT(index, v);
	}

	public void glVertex2d(double x, double y) {
		delegate.glVertex2d(x, y);
	}

	public void glVertex2dv(double[] v, int v_offset) {
		delegate.glVertex2dv(v, v_offset);
	}

	public void glVertex2dv(DoubleBuffer v) {
		delegate.glVertex2dv(v);
	}

	public void glVertex2f(float x, float y) {
		delegate.glVertex2f(x, y);
	}

	public void glVertex2fv(float[] v, int v_offset) {
		delegate.glVertex2fv(v, v_offset);
	}

	public void glVertex2fv(FloatBuffer v) {
		delegate.glVertex2fv(v);
	}

	public void glVertex2hNV(short x, short y) {
		delegate.glVertex2hNV(x, y);
	}

	public void glVertex2hvNV(short[] v, int v_offset) {
		delegate.glVertex2hvNV(v, v_offset);
	}

	public void glVertex2hvNV(ShortBuffer v) {
		delegate.glVertex2hvNV(v);
	}

	public void glVertex2i(int x, int y) {
		delegate.glVertex2i(x, y);
	}

	public void glVertex2iv(int[] v, int v_offset) {
		delegate.glVertex2iv(v, v_offset);
	}

	public void glVertex2iv(IntBuffer v) {
		delegate.glVertex2iv(v);
	}

	public void glVertex2s(short x, short y) {
		delegate.glVertex2s(x, y);
	}

	public void glVertex2sv(short[] v, int v_offset) {
		delegate.glVertex2sv(v, v_offset);
	}

	public void glVertex2sv(ShortBuffer v) {
		delegate.glVertex2sv(v);
	}

	public void glVertex3d(double x, double y, double z) {
		delegate.glVertex3d(x, y, z);
	}

	public void glVertex3dv(double[] v, int v_offset) {
		delegate.glVertex3dv(v, v_offset);
	}

	public void glVertex3dv(DoubleBuffer v) {
		delegate.glVertex3dv(v);
	}

	public void glVertex3f(float x, float y, float z) {
		delegate.glVertex3f(x, y, z);
	}

	public void glVertex3fv(float[] v, int v_offset) {
		delegate.glVertex3fv(v, v_offset);
	}

	public void glVertex3fv(FloatBuffer v) {
		delegate.glVertex3fv(v);
	}

	public void glVertex3hNV(short red, short green, short blue) {
		delegate.glVertex3hNV(red, green, blue);
	}

	public void glVertex3hvNV(short[] v, int v_offset) {
		delegate.glVertex3hvNV(v, v_offset);
	}

	public void glVertex3hvNV(ShortBuffer v) {
		delegate.glVertex3hvNV(v);
	}

	public void glVertex3i(int x, int y, int z) {
		delegate.glVertex3i(x, y, z);
	}

	public void glVertex3iv(int[] v, int v_offset) {
		delegate.glVertex3iv(v, v_offset);
	}

	public void glVertex3iv(IntBuffer v) {
		delegate.glVertex3iv(v);
	}

	public void glVertex3s(short x, short y, short z) {
		delegate.glVertex3s(x, y, z);
	}

	public void glVertex3sv(short[] v, int v_offset) {
		delegate.glVertex3sv(v, v_offset);
	}

	public void glVertex3sv(ShortBuffer v) {
		delegate.glVertex3sv(v);
	}

	public void glVertex4d(double x, double y, double z, double w) {
		delegate.glVertex4d(x, y, z, w);
	}

	public void glVertex4dv(double[] v, int v_offset) {
		delegate.glVertex4dv(v, v_offset);
	}

	public void glVertex4dv(DoubleBuffer v) {
		delegate.glVertex4dv(v);
	}

	public void glVertex4f(float x, float y, float z, float w) {
		delegate.glVertex4f(x, y, z, w);
	}

	public void glVertex4fv(float[] v, int v_offset) {
		delegate.glVertex4fv(v, v_offset);
	}

	public void glVertex4fv(FloatBuffer v) {
		delegate.glVertex4fv(v);
	}

	public void glVertex4hNV(short x, short y, short z, short w) {
		delegate.glVertex4hNV(x, y, z, w);
	}

	public void glVertex4hvNV(short[] v, int v_offset) {
		delegate.glVertex4hvNV(v, v_offset);
	}

	public void glVertex4hvNV(ShortBuffer v) {
		delegate.glVertex4hvNV(v);
	}

	public void glVertex4i(int x, int y, int z, int w) {
		delegate.glVertex4i(x, y, z, w);
	}

	public void glVertex4iv(int[] v, int v_offset) {
		delegate.glVertex4iv(v, v_offset);
	}

	public void glVertex4iv(IntBuffer v) {
		delegate.glVertex4iv(v);
	}

	public void glVertex4s(short x, short y, short z, short w) {
		delegate.glVertex4s(x, y, z, w);
	}

	public void glVertex4sv(short[] v, int v_offset) {
		delegate.glVertex4sv(v, v_offset);
	}

	public void glVertex4sv(ShortBuffer v) {
		delegate.glVertex4sv(v);
	}

	public void glVertexArrayParameteriAPPLE(int target, int s) {
		delegate.glVertexArrayParameteriAPPLE(target, s);
	}

	public void glVertexArrayRangeAPPLE(int length, Buffer pointer) {
		delegate.glVertexArrayRangeAPPLE(length, pointer);
	}

	public void glVertexArrayRangeNV(int length, Buffer pointer) {
		delegate.glVertexArrayRangeNV(length, pointer);
	}

	public void glVertexAttrib1d(int target, double s) {
		delegate.glVertexAttrib1d(target, s);
	}

	public void glVertexAttrib1dARB(int target, double s) {
		delegate.glVertexAttrib1dARB(target, s);
	}

	public void glVertexAttrib1dNV(int target, double s) {
		delegate.glVertexAttrib1dNV(target, s);
	}

	public void glVertexAttrib1dv(int target, double[] v, int v_offset) {
		delegate.glVertexAttrib1dv(target, v, v_offset);
	}

	public void glVertexAttrib1dv(int target, DoubleBuffer v) {
		delegate.glVertexAttrib1dv(target, v);
	}

	public void glVertexAttrib1dvARB(int target, double[] v, int v_offset) {
		delegate.glVertexAttrib1dvARB(target, v, v_offset);
	}

	public void glVertexAttrib1dvARB(int target, DoubleBuffer v) {
		delegate.glVertexAttrib1dvARB(target, v);
	}

	public void glVertexAttrib1dvNV(int target, double[] v, int v_offset) {
		delegate.glVertexAttrib1dvNV(target, v, v_offset);
	}

	public void glVertexAttrib1dvNV(int target, DoubleBuffer v) {
		delegate.glVertexAttrib1dvNV(target, v);
	}

	public void glVertexAttrib1f(int target, float s) {
		delegate.glVertexAttrib1f(target, s);
	}

	public void glVertexAttrib1fARB(int target, float s) {
		delegate.glVertexAttrib1fARB(target, s);
	}

	public void glVertexAttrib1fNV(int target, float s) {
		delegate.glVertexAttrib1fNV(target, s);
	}

	public void glVertexAttrib1fv(int target, float[] v, int v_offset) {
		delegate.glVertexAttrib1fv(target, v, v_offset);
	}

	public void glVertexAttrib1fv(int target, FloatBuffer v) {
		delegate.glVertexAttrib1fv(target, v);
	}

	public void glVertexAttrib1fvARB(int target, float[] v, int v_offset) {
		delegate.glVertexAttrib1fvARB(target, v, v_offset);
	}

	public void glVertexAttrib1fvARB(int target, FloatBuffer v) {
		delegate.glVertexAttrib1fvARB(target, v);
	}

	public void glVertexAttrib1fvNV(int target, float[] v, int v_offset) {
		delegate.glVertexAttrib1fvNV(target, v, v_offset);
	}

	public void glVertexAttrib1fvNV(int target, FloatBuffer v) {
		delegate.glVertexAttrib1fvNV(target, v);
	}

	public void glVertexAttrib1hNV(int target, short s) {
		delegate.glVertexAttrib1hNV(target, s);
	}

	public void glVertexAttrib1hvNV(int index, short[] v, int v_offset) {
		delegate.glVertexAttrib1hvNV(index, v, v_offset);
	}

	public void glVertexAttrib1hvNV(int index, ShortBuffer v) {
		delegate.glVertexAttrib1hvNV(index, v);
	}

	public void glVertexAttrib1s(int target, short s) {
		delegate.glVertexAttrib1s(target, s);
	}

	public void glVertexAttrib1sARB(int target, short s) {
		delegate.glVertexAttrib1sARB(target, s);
	}

	public void glVertexAttrib1sNV(int target, short s) {
		delegate.glVertexAttrib1sNV(target, s);
	}

	public void glVertexAttrib1sv(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib1sv(target, v, v_offset);
	}

	public void glVertexAttrib1sv(int target, ShortBuffer v) {
		delegate.glVertexAttrib1sv(target, v);
	}

	public void glVertexAttrib1svARB(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib1svARB(target, v, v_offset);
	}

	public void glVertexAttrib1svARB(int target, ShortBuffer v) {
		delegate.glVertexAttrib1svARB(target, v);
	}

	public void glVertexAttrib1svNV(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib1svNV(target, v, v_offset);
	}

	public void glVertexAttrib1svNV(int target, ShortBuffer v) {
		delegate.glVertexAttrib1svNV(target, v);
	}

	public void glVertexAttrib2d(int target, double s, double t) {
		delegate.glVertexAttrib2d(target, s, t);
	}

	public void glVertexAttrib2dARB(int target, double s, double t) {
		delegate.glVertexAttrib2dARB(target, s, t);
	}

	public void glVertexAttrib2dNV(int target, double s, double t) {
		delegate.glVertexAttrib2dNV(target, s, t);
	}

	public void glVertexAttrib2dv(int target, double[] v, int v_offset) {
		delegate.glVertexAttrib2dv(target, v, v_offset);
	}

	public void glVertexAttrib2dv(int target, DoubleBuffer v) {
		delegate.glVertexAttrib2dv(target, v);
	}

	public void glVertexAttrib2dvARB(int target, double[] v, int v_offset) {
		delegate.glVertexAttrib2dvARB(target, v, v_offset);
	}

	public void glVertexAttrib2dvARB(int target, DoubleBuffer v) {
		delegate.glVertexAttrib2dvARB(target, v);
	}

	public void glVertexAttrib2dvNV(int target, double[] v, int v_offset) {
		delegate.glVertexAttrib2dvNV(target, v, v_offset);
	}

	public void glVertexAttrib2dvNV(int target, DoubleBuffer v) {
		delegate.glVertexAttrib2dvNV(target, v);
	}

	public void glVertexAttrib2f(int target, float s, float t) {
		delegate.glVertexAttrib2f(target, s, t);
	}

	public void glVertexAttrib2fARB(int target, float s, float t) {
		delegate.glVertexAttrib2fARB(target, s, t);
	}

	public void glVertexAttrib2fNV(int target, float s, float t) {
		delegate.glVertexAttrib2fNV(target, s, t);
	}

	public void glVertexAttrib2fv(int target, float[] v, int v_offset) {
		delegate.glVertexAttrib2fv(target, v, v_offset);
	}

	public void glVertexAttrib2fv(int target, FloatBuffer v) {
		delegate.glVertexAttrib2fv(target, v);
	}

	public void glVertexAttrib2fvARB(int target, float[] v, int v_offset) {
		delegate.glVertexAttrib2fvARB(target, v, v_offset);
	}

	public void glVertexAttrib2fvARB(int target, FloatBuffer v) {
		delegate.glVertexAttrib2fvARB(target, v);
	}

	public void glVertexAttrib2fvNV(int target, float[] v, int v_offset) {
		delegate.glVertexAttrib2fvNV(target, v, v_offset);
	}

	public void glVertexAttrib2fvNV(int target, FloatBuffer v) {
		delegate.glVertexAttrib2fvNV(target, v);
	}

	public void glVertexAttrib2hNV(int target, short s, short t) {
		delegate.glVertexAttrib2hNV(target, s, t);
	}

	public void glVertexAttrib2hvNV(int index, short[] v, int v_offset) {
		delegate.glVertexAttrib2hvNV(index, v, v_offset);
	}

	public void glVertexAttrib2hvNV(int index, ShortBuffer v) {
		delegate.glVertexAttrib2hvNV(index, v);
	}

	public void glVertexAttrib2s(int target, short s, short t) {
		delegate.glVertexAttrib2s(target, s, t);
	}

	public void glVertexAttrib2sARB(int target, short s, short t) {
		delegate.glVertexAttrib2sARB(target, s, t);
	}

	public void glVertexAttrib2sNV(int target, short s, short t) {
		delegate.glVertexAttrib2sNV(target, s, t);
	}

	public void glVertexAttrib2sv(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib2sv(target, v, v_offset);
	}

	public void glVertexAttrib2sv(int target, ShortBuffer v) {
		delegate.glVertexAttrib2sv(target, v);
	}

	public void glVertexAttrib2svARB(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib2svARB(target, v, v_offset);
	}

	public void glVertexAttrib2svARB(int target, ShortBuffer v) {
		delegate.glVertexAttrib2svARB(target, v);
	}

	public void glVertexAttrib2svNV(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib2svNV(target, v, v_offset);
	}

	public void glVertexAttrib2svNV(int target, ShortBuffer v) {
		delegate.glVertexAttrib2svNV(target, v);
	}

	public void glVertexAttrib3d(int target, double s, double t, double r) {
		delegate.glVertexAttrib3d(target, s, t, r);
	}

	public void glVertexAttrib3dARB(int target, double s, double t, double r) {
		delegate.glVertexAttrib3dARB(target, s, t, r);
	}

	public void glVertexAttrib3dNV(int target, double s, double t, double r) {
		delegate.glVertexAttrib3dNV(target, s, t, r);
	}

	public void glVertexAttrib3dv(int target, double[] v, int v_offset) {
		delegate.glVertexAttrib3dv(target, v, v_offset);
	}

	public void glVertexAttrib3dv(int target, DoubleBuffer v) {
		delegate.glVertexAttrib3dv(target, v);
	}

	public void glVertexAttrib3dvARB(int target, double[] v, int v_offset) {
		delegate.glVertexAttrib3dvARB(target, v, v_offset);
	}

	public void glVertexAttrib3dvARB(int target, DoubleBuffer v) {
		delegate.glVertexAttrib3dvARB(target, v);
	}

	public void glVertexAttrib3dvNV(int target, double[] v, int v_offset) {
		delegate.glVertexAttrib3dvNV(target, v, v_offset);
	}

	public void glVertexAttrib3dvNV(int target, DoubleBuffer v) {
		delegate.glVertexAttrib3dvNV(target, v);
	}

	public void glVertexAttrib3f(int target, float s, float t, float r) {
		delegate.glVertexAttrib3f(target, s, t, r);
	}

	public void glVertexAttrib3fARB(int target, float s, float t, float r) {
		delegate.glVertexAttrib3fARB(target, s, t, r);
	}

	public void glVertexAttrib3fNV(int target, float s, float t, float r) {
		delegate.glVertexAttrib3fNV(target, s, t, r);
	}

	public void glVertexAttrib3fv(int target, float[] v, int v_offset) {
		delegate.glVertexAttrib3fv(target, v, v_offset);
	}

	public void glVertexAttrib3fv(int target, FloatBuffer v) {
		delegate.glVertexAttrib3fv(target, v);
	}

	public void glVertexAttrib3fvARB(int target, float[] v, int v_offset) {
		delegate.glVertexAttrib3fvARB(target, v, v_offset);
	}

	public void glVertexAttrib3fvARB(int target, FloatBuffer v) {
		delegate.glVertexAttrib3fvARB(target, v);
	}

	public void glVertexAttrib3fvNV(int target, float[] v, int v_offset) {
		delegate.glVertexAttrib3fvNV(target, v, v_offset);
	}

	public void glVertexAttrib3fvNV(int target, FloatBuffer v) {
		delegate.glVertexAttrib3fvNV(target, v);
	}

	public void glVertexAttrib3hNV(int target, short s, short t, short r) {
		delegate.glVertexAttrib3hNV(target, s, t, r);
	}

	public void glVertexAttrib3hvNV(int index, short[] v, int v_offset) {
		delegate.glVertexAttrib3hvNV(index, v, v_offset);
	}

	public void glVertexAttrib3hvNV(int index, ShortBuffer v) {
		delegate.glVertexAttrib3hvNV(index, v);
	}

	public void glVertexAttrib3s(int target, short s, short t, short r) {
		delegate.glVertexAttrib3s(target, s, t, r);
	}

	public void glVertexAttrib3sARB(int target, short s, short t, short r) {
		delegate.glVertexAttrib3sARB(target, s, t, r);
	}

	public void glVertexAttrib3sNV(int target, short s, short t, short r) {
		delegate.glVertexAttrib3sNV(target, s, t, r);
	}

	public void glVertexAttrib3sv(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib3sv(target, v, v_offset);
	}

	public void glVertexAttrib3sv(int target, ShortBuffer v) {
		delegate.glVertexAttrib3sv(target, v);
	}

	public void glVertexAttrib3svARB(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib3svARB(target, v, v_offset);
	}

	public void glVertexAttrib3svARB(int target, ShortBuffer v) {
		delegate.glVertexAttrib3svARB(target, v);
	}

	public void glVertexAttrib3svNV(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib3svNV(target, v, v_offset);
	}

	public void glVertexAttrib3svNV(int target, ShortBuffer v) {
		delegate.glVertexAttrib3svNV(target, v);
	}

	public void glVertexAttrib4bv(int index, byte[] v, int v_offset) {
		delegate.glVertexAttrib4bv(index, v, v_offset);
	}

	public void glVertexAttrib4bv(int index, ByteBuffer v) {
		delegate.glVertexAttrib4bv(index, v);
	}

	public void glVertexAttrib4bvARB(int index, byte[] v, int v_offset) {
		delegate.glVertexAttrib4bvARB(index, v, v_offset);
	}

	public void glVertexAttrib4bvARB(int index, ByteBuffer v) {
		delegate.glVertexAttrib4bvARB(index, v);
	}

	public void glVertexAttrib4d(int target, double s, double t, double r,
			double q) {
		delegate.glVertexAttrib4d(target, s, t, r, q);
	}

	public void glVertexAttrib4dARB(int target, double s, double t, double r,
			double q) {
		delegate.glVertexAttrib4dARB(target, s, t, r, q);
	}

	public void glVertexAttrib4dNV(int target, double s, double t, double r,
			double q) {
		delegate.glVertexAttrib4dNV(target, s, t, r, q);
	}

	public void glVertexAttrib4dv(int target, double[] v, int v_offset) {
		delegate.glVertexAttrib4dv(target, v, v_offset);
	}

	public void glVertexAttrib4dv(int target, DoubleBuffer v) {
		delegate.glVertexAttrib4dv(target, v);
	}

	public void glVertexAttrib4dvARB(int target, double[] v, int v_offset) {
		delegate.glVertexAttrib4dvARB(target, v, v_offset);
	}

	public void glVertexAttrib4dvARB(int target, DoubleBuffer v) {
		delegate.glVertexAttrib4dvARB(target, v);
	}

	public void glVertexAttrib4dvNV(int target, double[] v, int v_offset) {
		delegate.glVertexAttrib4dvNV(target, v, v_offset);
	}

	public void glVertexAttrib4dvNV(int target, DoubleBuffer v) {
		delegate.glVertexAttrib4dvNV(target, v);
	}

	public void glVertexAttrib4f(int target, float s, float t, float r, float q) {
		delegate.glVertexAttrib4f(target, s, t, r, q);
	}

	public void glVertexAttrib4fARB(int target, float s, float t, float r,
			float q) {
		delegate.glVertexAttrib4fARB(target, s, t, r, q);
	}

	public void glVertexAttrib4fNV(int target, float s, float t, float r,
			float q) {
		delegate.glVertexAttrib4fNV(target, s, t, r, q);
	}

	public void glVertexAttrib4fv(int target, float[] v, int v_offset) {
		delegate.glVertexAttrib4fv(target, v, v_offset);
	}

	public void glVertexAttrib4fv(int target, FloatBuffer v) {
		delegate.glVertexAttrib4fv(target, v);
	}

	public void glVertexAttrib4fvARB(int target, float[] v, int v_offset) {
		delegate.glVertexAttrib4fvARB(target, v, v_offset);
	}

	public void glVertexAttrib4fvARB(int target, FloatBuffer v) {
		delegate.glVertexAttrib4fvARB(target, v);
	}

	public void glVertexAttrib4fvNV(int target, float[] v, int v_offset) {
		delegate.glVertexAttrib4fvNV(target, v, v_offset);
	}

	public void glVertexAttrib4fvNV(int target, FloatBuffer v) {
		delegate.glVertexAttrib4fvNV(target, v);
	}

	public void glVertexAttrib4hNV(int target, short s, short t, short r,
			short q) {
		delegate.glVertexAttrib4hNV(target, s, t, r, q);
	}

	public void glVertexAttrib4hvNV(int index, short[] v, int v_offset) {
		delegate.glVertexAttrib4hvNV(index, v, v_offset);
	}

	public void glVertexAttrib4hvNV(int index, ShortBuffer v) {
		delegate.glVertexAttrib4hvNV(index, v);
	}

	public void glVertexAttrib4iv(int target, int[] v, int v_offset) {
		delegate.glVertexAttrib4iv(target, v, v_offset);
	}

	public void glVertexAttrib4iv(int target, IntBuffer v) {
		delegate.glVertexAttrib4iv(target, v);
	}

	public void glVertexAttrib4ivARB(int target, int[] v, int v_offset) {
		delegate.glVertexAttrib4ivARB(target, v, v_offset);
	}

	public void glVertexAttrib4ivARB(int target, IntBuffer v) {
		delegate.glVertexAttrib4ivARB(target, v);
	}

	public void glVertexAttrib4Nbv(int index, byte[] v, int v_offset) {
		delegate.glVertexAttrib4Nbv(index, v, v_offset);
	}

	public void glVertexAttrib4Nbv(int index, ByteBuffer v) {
		delegate.glVertexAttrib4Nbv(index, v);
	}

	public void glVertexAttrib4NbvARB(int index, byte[] v, int v_offset) {
		delegate.glVertexAttrib4NbvARB(index, v, v_offset);
	}

	public void glVertexAttrib4NbvARB(int index, ByteBuffer v) {
		delegate.glVertexAttrib4NbvARB(index, v);
	}

	public void glVertexAttrib4Niv(int target, int[] v, int v_offset) {
		delegate.glVertexAttrib4Niv(target, v, v_offset);
	}

	public void glVertexAttrib4Niv(int target, IntBuffer v) {
		delegate.glVertexAttrib4Niv(target, v);
	}

	public void glVertexAttrib4NivARB(int target, int[] v, int v_offset) {
		delegate.glVertexAttrib4NivARB(target, v, v_offset);
	}

	public void glVertexAttrib4NivARB(int target, IntBuffer v) {
		delegate.glVertexAttrib4NivARB(target, v);
	}

	public void glVertexAttrib4Nsv(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib4Nsv(target, v, v_offset);
	}

	public void glVertexAttrib4Nsv(int target, ShortBuffer v) {
		delegate.glVertexAttrib4Nsv(target, v);
	}

	public void glVertexAttrib4NsvARB(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib4NsvARB(target, v, v_offset);
	}

	public void glVertexAttrib4NsvARB(int target, ShortBuffer v) {
		delegate.glVertexAttrib4NsvARB(target, v);
	}

	public void glVertexAttrib4Nub(int index, byte x, byte y, byte z, byte w) {
		delegate.glVertexAttrib4Nub(index, x, y, z, w);
	}

	public void glVertexAttrib4NubARB(int index, byte x, byte y, byte z, byte w) {
		delegate.glVertexAttrib4NubARB(index, x, y, z, w);
	}

	public void glVertexAttrib4Nubv(int index, byte[] v, int v_offset) {
		delegate.glVertexAttrib4Nubv(index, v, v_offset);
	}

	public void glVertexAttrib4Nubv(int index, ByteBuffer v) {
		delegate.glVertexAttrib4Nubv(index, v);
	}

	public void glVertexAttrib4NubvARB(int index, byte[] v, int v_offset) {
		delegate.glVertexAttrib4NubvARB(index, v, v_offset);
	}

	public void glVertexAttrib4NubvARB(int index, ByteBuffer v) {
		delegate.glVertexAttrib4NubvARB(index, v);
	}

	public void glVertexAttrib4Nuiv(int index, int[] v, int v_offset) {
		delegate.glVertexAttrib4Nuiv(index, v, v_offset);
	}

	public void glVertexAttrib4Nuiv(int index, IntBuffer v) {
		delegate.glVertexAttrib4Nuiv(index, v);
	}

	public void glVertexAttrib4NuivARB(int index, int[] v, int v_offset) {
		delegate.glVertexAttrib4NuivARB(index, v, v_offset);
	}

	public void glVertexAttrib4NuivARB(int index, IntBuffer v) {
		delegate.glVertexAttrib4NuivARB(index, v);
	}

	public void glVertexAttrib4Nusv(int index, short[] v, int v_offset) {
		delegate.glVertexAttrib4Nusv(index, v, v_offset);
	}

	public void glVertexAttrib4Nusv(int index, ShortBuffer v) {
		delegate.glVertexAttrib4Nusv(index, v);
	}

	public void glVertexAttrib4NusvARB(int index, short[] v, int v_offset) {
		delegate.glVertexAttrib4NusvARB(index, v, v_offset);
	}

	public void glVertexAttrib4NusvARB(int index, ShortBuffer v) {
		delegate.glVertexAttrib4NusvARB(index, v);
	}

	public void glVertexAttrib4s(int target, short s, short t, short r, short q) {
		delegate.glVertexAttrib4s(target, s, t, r, q);
	}

	public void glVertexAttrib4sARB(int target, short s, short t, short r,
			short q) {
		delegate.glVertexAttrib4sARB(target, s, t, r, q);
	}

	public void glVertexAttrib4sNV(int target, short s, short t, short r,
			short q) {
		delegate.glVertexAttrib4sNV(target, s, t, r, q);
	}

	public void glVertexAttrib4sv(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib4sv(target, v, v_offset);
	}

	public void glVertexAttrib4sv(int target, ShortBuffer v) {
		delegate.glVertexAttrib4sv(target, v);
	}

	public void glVertexAttrib4svARB(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib4svARB(target, v, v_offset);
	}

	public void glVertexAttrib4svARB(int target, ShortBuffer v) {
		delegate.glVertexAttrib4svARB(target, v);
	}

	public void glVertexAttrib4svNV(int target, short[] v, int v_offset) {
		delegate.glVertexAttrib4svNV(target, v, v_offset);
	}

	public void glVertexAttrib4svNV(int target, ShortBuffer v) {
		delegate.glVertexAttrib4svNV(target, v);
	}

	public void glVertexAttrib4ubNV(int index, byte x, byte y, byte z, byte w) {
		delegate.glVertexAttrib4ubNV(index, x, y, z, w);
	}

	public void glVertexAttrib4ubv(int index, byte[] v, int v_offset) {
		delegate.glVertexAttrib4ubv(index, v, v_offset);
	}

	public void glVertexAttrib4ubv(int index, ByteBuffer v) {
		delegate.glVertexAttrib4ubv(index, v);
	}

	public void glVertexAttrib4ubvARB(int index, byte[] v, int v_offset) {
		delegate.glVertexAttrib4ubvARB(index, v, v_offset);
	}

	public void glVertexAttrib4ubvARB(int index, ByteBuffer v) {
		delegate.glVertexAttrib4ubvARB(index, v);
	}

	public void glVertexAttrib4ubvNV(int index, byte[] v, int v_offset) {
		delegate.glVertexAttrib4ubvNV(index, v, v_offset);
	}

	public void glVertexAttrib4ubvNV(int index, ByteBuffer v) {
		delegate.glVertexAttrib4ubvNV(index, v);
	}

	public void glVertexAttrib4uiv(int index, int[] v, int v_offset) {
		delegate.glVertexAttrib4uiv(index, v, v_offset);
	}

	public void glVertexAttrib4uiv(int index, IntBuffer v) {
		delegate.glVertexAttrib4uiv(index, v);
	}

	public void glVertexAttrib4uivARB(int index, int[] v, int v_offset) {
		delegate.glVertexAttrib4uivARB(index, v, v_offset);
	}

	public void glVertexAttrib4uivARB(int index, IntBuffer v) {
		delegate.glVertexAttrib4uivARB(index, v);
	}

	public void glVertexAttrib4usv(int index, short[] v, int v_offset) {
		delegate.glVertexAttrib4usv(index, v, v_offset);
	}

	public void glVertexAttrib4usv(int index, ShortBuffer v) {
		delegate.glVertexAttrib4usv(index, v);
	}

	public void glVertexAttrib4usvARB(int index, short[] v, int v_offset) {
		delegate.glVertexAttrib4usvARB(index, v, v_offset);
	}

	public void glVertexAttrib4usvARB(int index, ShortBuffer v) {
		delegate.glVertexAttrib4usvARB(index, v);
	}

	public void glVertexAttribArrayObjectATI(int index, int size, int type,
			boolean normalized, int stride, int buffer, int offset) {
		delegate.glVertexAttribArrayObjectATI(index, size, type, normalized,
				stride, buffer, offset);
	}

	public void glVertexAttribI1iEXT(int index, int x) {
		delegate.glVertexAttribI1iEXT(index, x);
	}

	public void glVertexAttribI1ivEXT(int index, int[] v, int v_offset) {
		delegate.glVertexAttribI1ivEXT(index, v, v_offset);
	}

	public void glVertexAttribI1ivEXT(int index, IntBuffer v) {
		delegate.glVertexAttribI1ivEXT(index, v);
	}

	public void glVertexAttribI1uiEXT(int index, int x) {
		delegate.glVertexAttribI1uiEXT(index, x);
	}

	public void glVertexAttribI1uivEXT(int index, int[] v, int v_offset) {
		delegate.glVertexAttribI1uivEXT(index, v, v_offset);
	}

	public void glVertexAttribI1uivEXT(int index, IntBuffer v) {
		delegate.glVertexAttribI1uivEXT(index, v);
	}

	public void glVertexAttribI2iEXT(int index, int x, int y) {
		delegate.glVertexAttribI2iEXT(index, x, y);
	}

	public void glVertexAttribI2ivEXT(int index, int[] v, int v_offset) {
		delegate.glVertexAttribI2ivEXT(index, v, v_offset);
	}

	public void glVertexAttribI2ivEXT(int index, IntBuffer v) {
		delegate.glVertexAttribI2ivEXT(index, v);
	}

	public void glVertexAttribI2uiEXT(int index, int x, int y) {
		delegate.glVertexAttribI2uiEXT(index, x, y);
	}

	public void glVertexAttribI2uivEXT(int index, int[] v, int v_offset) {
		delegate.glVertexAttribI2uivEXT(index, v, v_offset);
	}

	public void glVertexAttribI2uivEXT(int index, IntBuffer v) {
		delegate.glVertexAttribI2uivEXT(index, v);
	}

	public void glVertexAttribI3iEXT(int index, int x, int y, int z) {
		delegate.glVertexAttribI3iEXT(index, x, y, z);
	}

	public void glVertexAttribI3ivEXT(int index, int[] v, int v_offset) {
		delegate.glVertexAttribI3ivEXT(index, v, v_offset);
	}

	public void glVertexAttribI3ivEXT(int index, IntBuffer v) {
		delegate.glVertexAttribI3ivEXT(index, v);
	}

	public void glVertexAttribI3uiEXT(int index, int x, int y, int z) {
		delegate.glVertexAttribI3uiEXT(index, x, y, z);
	}

	public void glVertexAttribI3uivEXT(int index, int[] v, int v_offset) {
		delegate.glVertexAttribI3uivEXT(index, v, v_offset);
	}

	public void glVertexAttribI3uivEXT(int index, IntBuffer v) {
		delegate.glVertexAttribI3uivEXT(index, v);
	}

	public void glVertexAttribI4bvEXT(int index, byte[] v, int v_offset) {
		delegate.glVertexAttribI4bvEXT(index, v, v_offset);
	}

	public void glVertexAttribI4bvEXT(int index, ByteBuffer v) {
		delegate.glVertexAttribI4bvEXT(index, v);
	}

	public void glVertexAttribI4iEXT(int index, int x, int y, int z, int w) {
		delegate.glVertexAttribI4iEXT(index, x, y, z, w);
	}

	public void glVertexAttribI4ivEXT(int index, int[] v, int v_offset) {
		delegate.glVertexAttribI4ivEXT(index, v, v_offset);
	}

	public void glVertexAttribI4ivEXT(int index, IntBuffer v) {
		delegate.glVertexAttribI4ivEXT(index, v);
	}

	public void glVertexAttribI4svEXT(int index, short[] v, int v_offset) {
		delegate.glVertexAttribI4svEXT(index, v, v_offset);
	}

	public void glVertexAttribI4svEXT(int index, ShortBuffer v) {
		delegate.glVertexAttribI4svEXT(index, v);
	}

	public void glVertexAttribI4ubvEXT(int index, byte[] v, int v_offset) {
		delegate.glVertexAttribI4ubvEXT(index, v, v_offset);
	}

	public void glVertexAttribI4ubvEXT(int index, ByteBuffer v) {
		delegate.glVertexAttribI4ubvEXT(index, v);
	}

	public void glVertexAttribI4uiEXT(int index, int x, int y, int z, int w) {
		delegate.glVertexAttribI4uiEXT(index, x, y, z, w);
	}

	public void glVertexAttribI4uivEXT(int index, int[] v, int v_offset) {
		delegate.glVertexAttribI4uivEXT(index, v, v_offset);
	}

	public void glVertexAttribI4uivEXT(int index, IntBuffer v) {
		delegate.glVertexAttribI4uivEXT(index, v);
	}

	public void glVertexAttribI4usvEXT(int index, short[] v, int v_offset) {
		delegate.glVertexAttribI4usvEXT(index, v, v_offset);
	}

	public void glVertexAttribI4usvEXT(int index, ShortBuffer v) {
		delegate.glVertexAttribI4usvEXT(index, v);
	}

	public void glVertexAttribIPointerEXT(int index, int size, int type,
			int stride, Buffer pointer) {
		delegate.glVertexAttribIPointerEXT(index, size, type, stride, pointer);
	}

	public void glVertexAttribPointer(int index, int size, int type,
			boolean normalized, int stride, Buffer pointer) {
		delegate.glVertexAttribPointer(index, size, type, normalized, stride,
				pointer);
	}

	public void glVertexAttribPointer(int index, int size, int type,
			boolean normalized, int stride, long pointer_buffer_offset) {
		delegate.glVertexAttribPointer(index, size, type, normalized, stride,
				pointer_buffer_offset);
	}

	public void glVertexAttribPointerARB(int index, int size, int type,
			boolean normalized, int stride, Buffer pointer) {
		delegate.glVertexAttribPointerARB(index, size, type, normalized,
				stride, pointer);
	}

	public void glVertexAttribPointerARB(int index, int size, int type,
			boolean normalized, int stride, long pointer_buffer_offset) {
		delegate.glVertexAttribPointerARB(index, size, type, normalized,
				stride, pointer_buffer_offset);
	}

	public void glVertexAttribPointerNV(int index, int fsize, int type,
			int stride, Buffer pointer) {
		delegate.glVertexAttribPointerNV(index, fsize, type, stride, pointer);
	}

	public void glVertexAttribPointerNV(int index, int fsize, int type,
			int stride, long pointer_buffer_offset) {
		delegate.glVertexAttribPointerNV(index, fsize, type, stride,
				pointer_buffer_offset);
	}

	public void glVertexAttribs1dvNV(int index, int count, double[] v,
			int v_offset) {
		delegate.glVertexAttribs1dvNV(index, count, v, v_offset);
	}

	public void glVertexAttribs1dvNV(int index, int count, DoubleBuffer v) {
		delegate.glVertexAttribs1dvNV(index, count, v);
	}

	public void glVertexAttribs1fvNV(int target, int n, float[] points,
			int points_offset) {
		delegate.glVertexAttribs1fvNV(target, n, points, points_offset);
	}

	public void glVertexAttribs1fvNV(int target, int n, FloatBuffer points) {
		delegate.glVertexAttribs1fvNV(target, n, points);
	}

	public void glVertexAttribs1hvNV(int index, int n, short[] v, int v_offset) {
		delegate.glVertexAttribs1hvNV(index, n, v, v_offset);
	}

	public void glVertexAttribs1hvNV(int index, int n, ShortBuffer v) {
		delegate.glVertexAttribs1hvNV(index, n, v);
	}

	public void glVertexAttribs1svNV(int index, int count, short[] v,
			int v_offset) {
		delegate.glVertexAttribs1svNV(index, count, v, v_offset);
	}

	public void glVertexAttribs1svNV(int index, int count, ShortBuffer v) {
		delegate.glVertexAttribs1svNV(index, count, v);
	}

	public void glVertexAttribs2dvNV(int index, int count, double[] v,
			int v_offset) {
		delegate.glVertexAttribs2dvNV(index, count, v, v_offset);
	}

	public void glVertexAttribs2dvNV(int index, int count, DoubleBuffer v) {
		delegate.glVertexAttribs2dvNV(index, count, v);
	}

	public void glVertexAttribs2fvNV(int target, int n, float[] points,
			int points_offset) {
		delegate.glVertexAttribs2fvNV(target, n, points, points_offset);
	}

	public void glVertexAttribs2fvNV(int target, int n, FloatBuffer points) {
		delegate.glVertexAttribs2fvNV(target, n, points);
	}

	public void glVertexAttribs2hvNV(int index, int n, short[] v, int v_offset) {
		delegate.glVertexAttribs2hvNV(index, n, v, v_offset);
	}

	public void glVertexAttribs2hvNV(int index, int n, ShortBuffer v) {
		delegate.glVertexAttribs2hvNV(index, n, v);
	}

	public void glVertexAttribs2svNV(int index, int count, short[] v,
			int v_offset) {
		delegate.glVertexAttribs2svNV(index, count, v, v_offset);
	}

	public void glVertexAttribs2svNV(int index, int count, ShortBuffer v) {
		delegate.glVertexAttribs2svNV(index, count, v);
	}

	public void glVertexAttribs3dvNV(int index, int count, double[] v,
			int v_offset) {
		delegate.glVertexAttribs3dvNV(index, count, v, v_offset);
	}

	public void glVertexAttribs3dvNV(int index, int count, DoubleBuffer v) {
		delegate.glVertexAttribs3dvNV(index, count, v);
	}

	public void glVertexAttribs3fvNV(int target, int n, float[] points,
			int points_offset) {
		delegate.glVertexAttribs3fvNV(target, n, points, points_offset);
	}

	public void glVertexAttribs3fvNV(int target, int n, FloatBuffer points) {
		delegate.glVertexAttribs3fvNV(target, n, points);
	}

	public void glVertexAttribs3hvNV(int index, int n, short[] v, int v_offset) {
		delegate.glVertexAttribs3hvNV(index, n, v, v_offset);
	}

	public void glVertexAttribs3hvNV(int index, int n, ShortBuffer v) {
		delegate.glVertexAttribs3hvNV(index, n, v);
	}

	public void glVertexAttribs3svNV(int index, int count, short[] v,
			int v_offset) {
		delegate.glVertexAttribs3svNV(index, count, v, v_offset);
	}

	public void glVertexAttribs3svNV(int index, int count, ShortBuffer v) {
		delegate.glVertexAttribs3svNV(index, count, v);
	}

	public void glVertexAttribs4dvNV(int index, int count, double[] v,
			int v_offset) {
		delegate.glVertexAttribs4dvNV(index, count, v, v_offset);
	}

	public void glVertexAttribs4dvNV(int index, int count, DoubleBuffer v) {
		delegate.glVertexAttribs4dvNV(index, count, v);
	}

	public void glVertexAttribs4fvNV(int target, int n, float[] points,
			int points_offset) {
		delegate.glVertexAttribs4fvNV(target, n, points, points_offset);
	}

	public void glVertexAttribs4fvNV(int target, int n, FloatBuffer points) {
		delegate.glVertexAttribs4fvNV(target, n, points);
	}

	public void glVertexAttribs4hvNV(int index, int n, short[] v, int v_offset) {
		delegate.glVertexAttribs4hvNV(index, n, v, v_offset);
	}

	public void glVertexAttribs4hvNV(int index, int n, ShortBuffer v) {
		delegate.glVertexAttribs4hvNV(index, n, v);
	}

	public void glVertexAttribs4svNV(int index, int count, short[] v,
			int v_offset) {
		delegate.glVertexAttribs4svNV(index, count, v, v_offset);
	}

	public void glVertexAttribs4svNV(int index, int count, ShortBuffer v) {
		delegate.glVertexAttribs4svNV(index, count, v);
	}

	public void glVertexAttribs4ubvNV(int index, int count, byte[] v,
			int v_offset) {
		delegate.glVertexAttribs4ubvNV(index, count, v, v_offset);
	}

	public void glVertexAttribs4ubvNV(int index, int count, ByteBuffer v) {
		delegate.glVertexAttribs4ubvNV(index, count, v);
	}

	public void glVertexBlendARB(int count) {
		delegate.glVertexBlendARB(count);
	}

	public void glVertexBlendEnvfATI(int target, float s) {
		delegate.glVertexBlendEnvfATI(target, s);
	}

	public void glVertexBlendEnviATI(int target, int s) {
		delegate.glVertexBlendEnviATI(target, s);
	}

	public void glVertexPointer(int size, int type, int stride, Buffer ptr) {
		delegate.glVertexPointer(size, type, stride, ptr);
	}

	public void glVertexPointer(int size, int type, int stride,
			long ptr_buffer_offset) {
		delegate.glVertexPointer(size, type, stride, ptr_buffer_offset);
	}

	public void glVertexStream1dATI(int target, double s) {
		delegate.glVertexStream1dATI(target, s);
	}

	public void glVertexStream1dvATI(int target, double[] v, int v_offset) {
		delegate.glVertexStream1dvATI(target, v, v_offset);
	}

	public void glVertexStream1dvATI(int target, DoubleBuffer v) {
		delegate.glVertexStream1dvATI(target, v);
	}

	public void glVertexStream1fATI(int target, float s) {
		delegate.glVertexStream1fATI(target, s);
	}

	public void glVertexStream1fvATI(int target, float[] v, int v_offset) {
		delegate.glVertexStream1fvATI(target, v, v_offset);
	}

	public void glVertexStream1fvATI(int target, FloatBuffer v) {
		delegate.glVertexStream1fvATI(target, v);
	}

	public void glVertexStream1iATI(int target, int s) {
		delegate.glVertexStream1iATI(target, s);
	}

	public void glVertexStream1ivATI(int target, int[] v, int v_offset) {
		delegate.glVertexStream1ivATI(target, v, v_offset);
	}

	public void glVertexStream1ivATI(int target, IntBuffer v) {
		delegate.glVertexStream1ivATI(target, v);
	}

	public void glVertexStream1sATI(int target, short s) {
		delegate.glVertexStream1sATI(target, s);
	}

	public void glVertexStream1svATI(int target, short[] v, int v_offset) {
		delegate.glVertexStream1svATI(target, v, v_offset);
	}

	public void glVertexStream1svATI(int target, ShortBuffer v) {
		delegate.glVertexStream1svATI(target, v);
	}

	public void glVertexStream2dATI(int target, double s, double t) {
		delegate.glVertexStream2dATI(target, s, t);
	}

	public void glVertexStream2dvATI(int target, double[] v, int v_offset) {
		delegate.glVertexStream2dvATI(target, v, v_offset);
	}

	public void glVertexStream2dvATI(int target, DoubleBuffer v) {
		delegate.glVertexStream2dvATI(target, v);
	}

	public void glVertexStream2fATI(int target, float s, float t) {
		delegate.glVertexStream2fATI(target, s, t);
	}

	public void glVertexStream2fvATI(int target, float[] v, int v_offset) {
		delegate.glVertexStream2fvATI(target, v, v_offset);
	}

	public void glVertexStream2fvATI(int target, FloatBuffer v) {
		delegate.glVertexStream2fvATI(target, v);
	}

	public void glVertexStream2iATI(int target, int s, int t) {
		delegate.glVertexStream2iATI(target, s, t);
	}

	public void glVertexStream2ivATI(int target, int[] v, int v_offset) {
		delegate.glVertexStream2ivATI(target, v, v_offset);
	}

	public void glVertexStream2ivATI(int target, IntBuffer v) {
		delegate.glVertexStream2ivATI(target, v);
	}

	public void glVertexStream2sATI(int target, short s, short t) {
		delegate.glVertexStream2sATI(target, s, t);
	}

	public void glVertexStream2svATI(int target, short[] v, int v_offset) {
		delegate.glVertexStream2svATI(target, v, v_offset);
	}

	public void glVertexStream2svATI(int target, ShortBuffer v) {
		delegate.glVertexStream2svATI(target, v);
	}

	public void glVertexStream3dATI(int target, double s, double t, double r) {
		delegate.glVertexStream3dATI(target, s, t, r);
	}

	public void glVertexStream3dvATI(int target, double[] v, int v_offset) {
		delegate.glVertexStream3dvATI(target, v, v_offset);
	}

	public void glVertexStream3dvATI(int target, DoubleBuffer v) {
		delegate.glVertexStream3dvATI(target, v);
	}

	public void glVertexStream3fATI(int target, float s, float t, float r) {
		delegate.glVertexStream3fATI(target, s, t, r);
	}

	public void glVertexStream3fvATI(int target, float[] v, int v_offset) {
		delegate.glVertexStream3fvATI(target, v, v_offset);
	}

	public void glVertexStream3fvATI(int target, FloatBuffer v) {
		delegate.glVertexStream3fvATI(target, v);
	}

	public void glVertexStream3iATI(int target, int s, int t, int r) {
		delegate.glVertexStream3iATI(target, s, t, r);
	}

	public void glVertexStream3ivATI(int target, int[] v, int v_offset) {
		delegate.glVertexStream3ivATI(target, v, v_offset);
	}

	public void glVertexStream3ivATI(int target, IntBuffer v) {
		delegate.glVertexStream3ivATI(target, v);
	}

	public void glVertexStream3sATI(int target, short s, short t, short r) {
		delegate.glVertexStream3sATI(target, s, t, r);
	}

	public void glVertexStream3svATI(int target, short[] v, int v_offset) {
		delegate.glVertexStream3svATI(target, v, v_offset);
	}

	public void glVertexStream3svATI(int target, ShortBuffer v) {
		delegate.glVertexStream3svATI(target, v);
	}

	public void glVertexStream4dATI(int target, double s, double t, double r,
			double q) {
		delegate.glVertexStream4dATI(target, s, t, r, q);
	}

	public void glVertexStream4dvATI(int target, double[] v, int v_offset) {
		delegate.glVertexStream4dvATI(target, v, v_offset);
	}

	public void glVertexStream4dvATI(int target, DoubleBuffer v) {
		delegate.glVertexStream4dvATI(target, v);
	}

	public void glVertexStream4fATI(int target, float s, float t, float r,
			float q) {
		delegate.glVertexStream4fATI(target, s, t, r, q);
	}

	public void glVertexStream4fvATI(int target, float[] v, int v_offset) {
		delegate.glVertexStream4fvATI(target, v, v_offset);
	}

	public void glVertexStream4fvATI(int target, FloatBuffer v) {
		delegate.glVertexStream4fvATI(target, v);
	}

	public void glVertexStream4iATI(int target, int start, int x, int y,
			int width) {
		delegate.glVertexStream4iATI(target, start, x, y, width);
	}

	public void glVertexStream4ivATI(int target, int[] v, int v_offset) {
		delegate.glVertexStream4ivATI(target, v, v_offset);
	}

	public void glVertexStream4ivATI(int target, IntBuffer v) {
		delegate.glVertexStream4ivATI(target, v);
	}

	public void glVertexStream4sATI(int target, short s, short t, short r,
			short q) {
		delegate.glVertexStream4sATI(target, s, t, r, q);
	}

	public void glVertexStream4svATI(int target, short[] v, int v_offset) {
		delegate.glVertexStream4svATI(target, v, v_offset);
	}

	public void glVertexStream4svATI(int target, ShortBuffer v) {
		delegate.glVertexStream4svATI(target, v);
	}

	public void glVertexWeightfEXT(float coord) {
		delegate.glVertexWeightfEXT(coord);
	}

	public void glVertexWeightfvEXT(float[] m, int m_offset) {
		delegate.glVertexWeightfvEXT(m, m_offset);
	}

	public void glVertexWeightfvEXT(FloatBuffer m) {
		delegate.glVertexWeightfvEXT(m);
	}

	public void glVertexWeighthNV(short factor) {
		delegate.glVertexWeighthNV(factor);
	}

	public void glVertexWeighthvNV(short[] v, int v_offset) {
		delegate.glVertexWeighthvNV(v, v_offset);
	}

	public void glVertexWeighthvNV(ShortBuffer v) {
		delegate.glVertexWeighthvNV(v);
	}

	public void glVertexWeightPointerEXT(int size, int type, int stride,
			Buffer pointer) {
		delegate.glVertexWeightPointerEXT(size, type, stride, pointer);
	}

	public void glVertexWeightPointerEXT(int size, int type, int stride,
			long pointer_buffer_offset) {
		delegate.glVertexWeightPointerEXT(size, type, stride,
				pointer_buffer_offset);
	}

	public void glViewport(int x, int y, int width, int height) {
		delegate.glViewport(x, y, width, height);
	}

	public void glWeightbvARB(int size, byte[] weights, int weights_offset) {
		delegate.glWeightbvARB(size, weights, weights_offset);
	}

	public void glWeightbvARB(int size, ByteBuffer weights) {
		delegate.glWeightbvARB(size, weights);
	}

	public void glWeightdvARB(int size, double[] weights, int weights_offset) {
		delegate.glWeightdvARB(size, weights, weights_offset);
	}

	public void glWeightdvARB(int size, DoubleBuffer weights) {
		delegate.glWeightdvARB(size, weights);
	}

	public void glWeightfvARB(int size, float[] weights, int weights_offset) {
		delegate.glWeightfvARB(size, weights, weights_offset);
	}

	public void glWeightfvARB(int size, FloatBuffer weights) {
		delegate.glWeightfvARB(size, weights);
	}

	public void glWeightivARB(int size, int[] weights, int weights_offset) {
		delegate.glWeightivARB(size, weights, weights_offset);
	}

	public void glWeightivARB(int size, IntBuffer weights) {
		delegate.glWeightivARB(size, weights);
	}

	public void glWeightPointerARB(int size, int type, int stride,
			Buffer pointer) {
		delegate.glWeightPointerARB(size, type, stride, pointer);
	}

	public void glWeightPointerARB(int size, int type, int stride,
			long pointer_buffer_offset) {
		delegate.glWeightPointerARB(size, type, stride, pointer_buffer_offset);
	}

	public void glWeightsvARB(int size, short[] weights, int weights_offset) {
		delegate.glWeightsvARB(size, weights, weights_offset);
	}

	public void glWeightsvARB(int size, ShortBuffer weights) {
		delegate.glWeightsvARB(size, weights);
	}

	public void glWeightubvARB(int size, byte[] weights, int weights_offset) {
		delegate.glWeightubvARB(size, weights, weights_offset);
	}

	public void glWeightubvARB(int size, ByteBuffer weights) {
		delegate.glWeightubvARB(size, weights);
	}

	public void glWeightuivARB(int n, int[] ids, int ids_offset) {
		delegate.glWeightuivARB(n, ids, ids_offset);
	}

	public void glWeightuivARB(int n, IntBuffer ids) {
		delegate.glWeightuivARB(n, ids);
	}

	public void glWeightusvARB(int size, short[] weights, int weights_offset) {
		delegate.glWeightusvARB(size, weights, weights_offset);
	}

	public void glWeightusvARB(int size, ShortBuffer weights) {
		delegate.glWeightusvARB(size, weights);
	}

	public void glWindowPos2d(double x, double y) {
		delegate.glWindowPos2d(x, y);
	}

	public void glWindowPos2dARB(double x, double y) {
		delegate.glWindowPos2dARB(x, y);
	}

	public void glWindowPos2dMESA(double x, double y) {
		delegate.glWindowPos2dMESA(x, y);
	}

	public void glWindowPos2dv(double[] m, int m_offset) {
		delegate.glWindowPos2dv(m, m_offset);
	}

	public void glWindowPos2dv(DoubleBuffer m) {
		delegate.glWindowPos2dv(m);
	}

	public void glWindowPos2dvARB(double[] m, int m_offset) {
		delegate.glWindowPos2dvARB(m, m_offset);
	}

	public void glWindowPos2dvARB(DoubleBuffer m) {
		delegate.glWindowPos2dvARB(m);
	}

	public void glWindowPos2dvMESA(double[] m, int m_offset) {
		delegate.glWindowPos2dvMESA(m, m_offset);
	}

	public void glWindowPos2dvMESA(DoubleBuffer m) {
		delegate.glWindowPos2dvMESA(m);
	}

	public void glWindowPos2f(float x, float y) {
		delegate.glWindowPos2f(x, y);
	}

	public void glWindowPos2fARB(float x, float y) {
		delegate.glWindowPos2fARB(x, y);
	}

	public void glWindowPos2fMESA(float x, float y) {
		delegate.glWindowPos2fMESA(x, y);
	}

	public void glWindowPos2fv(float[] m, int m_offset) {
		delegate.glWindowPos2fv(m, m_offset);
	}

	public void glWindowPos2fv(FloatBuffer m) {
		delegate.glWindowPos2fv(m);
	}

	public void glWindowPos2fvARB(float[] m, int m_offset) {
		delegate.glWindowPos2fvARB(m, m_offset);
	}

	public void glWindowPos2fvARB(FloatBuffer m) {
		delegate.glWindowPos2fvARB(m);
	}

	public void glWindowPos2fvMESA(float[] m, int m_offset) {
		delegate.glWindowPos2fvMESA(m, m_offset);
	}

	public void glWindowPos2fvMESA(FloatBuffer m) {
		delegate.glWindowPos2fvMESA(m);
	}

	public void glWindowPos2i(int x, int y) {
		delegate.glWindowPos2i(x, y);
	}

	public void glWindowPos2iARB(int x, int y) {
		delegate.glWindowPos2iARB(x, y);
	}

	public void glWindowPos2iMESA(int x, int y) {
		delegate.glWindowPos2iMESA(x, y);
	}

	public void glWindowPos2iv(int[] v, int v_offset) {
		delegate.glWindowPos2iv(v, v_offset);
	}

	public void glWindowPos2iv(IntBuffer v) {
		delegate.glWindowPos2iv(v);
	}

	public void glWindowPos2ivARB(int[] v, int v_offset) {
		delegate.glWindowPos2ivARB(v, v_offset);
	}

	public void glWindowPos2ivARB(IntBuffer v) {
		delegate.glWindowPos2ivARB(v);
	}

	public void glWindowPos2ivMESA(int[] v, int v_offset) {
		delegate.glWindowPos2ivMESA(v, v_offset);
	}

	public void glWindowPos2ivMESA(IntBuffer v) {
		delegate.glWindowPos2ivMESA(v);
	}

	public void glWindowPos2s(short x, short y) {
		delegate.glWindowPos2s(x, y);
	}

	public void glWindowPos2sARB(short x, short y) {
		delegate.glWindowPos2sARB(x, y);
	}

	public void glWindowPos2sMESA(short x, short y) {
		delegate.glWindowPos2sMESA(x, y);
	}

	public void glWindowPos2sv(short[] v, int v_offset) {
		delegate.glWindowPos2sv(v, v_offset);
	}

	public void glWindowPos2sv(ShortBuffer v) {
		delegate.glWindowPos2sv(v);
	}

	public void glWindowPos2svARB(short[] v, int v_offset) {
		delegate.glWindowPos2svARB(v, v_offset);
	}

	public void glWindowPos2svARB(ShortBuffer v) {
		delegate.glWindowPos2svARB(v);
	}

	public void glWindowPos2svMESA(short[] v, int v_offset) {
		delegate.glWindowPos2svMESA(v, v_offset);
	}

	public void glWindowPos2svMESA(ShortBuffer v) {
		delegate.glWindowPos2svMESA(v);
	}

	public void glWindowPos3d(double red, double green, double blue) {
		delegate.glWindowPos3d(red, green, blue);
	}

	public void glWindowPos3dARB(double red, double green, double blue) {
		delegate.glWindowPos3dARB(red, green, blue);
	}

	public void glWindowPos3dMESA(double red, double green, double blue) {
		delegate.glWindowPos3dMESA(red, green, blue);
	}

	public void glWindowPos3dv(double[] m, int m_offset) {
		delegate.glWindowPos3dv(m, m_offset);
	}

	public void glWindowPos3dv(DoubleBuffer m) {
		delegate.glWindowPos3dv(m);
	}

	public void glWindowPos3dvARB(double[] m, int m_offset) {
		delegate.glWindowPos3dvARB(m, m_offset);
	}

	public void glWindowPos3dvARB(DoubleBuffer m) {
		delegate.glWindowPos3dvARB(m);
	}

	public void glWindowPos3dvMESA(double[] m, int m_offset) {
		delegate.glWindowPos3dvMESA(m, m_offset);
	}

	public void glWindowPos3dvMESA(DoubleBuffer m) {
		delegate.glWindowPos3dvMESA(m);
	}

	public void glWindowPos3f(float red, float green, float blue) {
		delegate.glWindowPos3f(red, green, blue);
	}

	public void glWindowPos3fARB(float red, float green, float blue) {
		delegate.glWindowPos3fARB(red, green, blue);
	}

	public void glWindowPos3fMESA(float red, float green, float blue) {
		delegate.glWindowPos3fMESA(red, green, blue);
	}

	public void glWindowPos3fv(float[] m, int m_offset) {
		delegate.glWindowPos3fv(m, m_offset);
	}

	public void glWindowPos3fv(FloatBuffer m) {
		delegate.glWindowPos3fv(m);
	}

	public void glWindowPos3fvARB(float[] m, int m_offset) {
		delegate.glWindowPos3fvARB(m, m_offset);
	}

	public void glWindowPos3fvARB(FloatBuffer m) {
		delegate.glWindowPos3fvARB(m);
	}

	public void glWindowPos3fvMESA(float[] m, int m_offset) {
		delegate.glWindowPos3fvMESA(m, m_offset);
	}

	public void glWindowPos3fvMESA(FloatBuffer m) {
		delegate.glWindowPos3fvMESA(m);
	}

	public void glWindowPos3i(int red, int green, int blue) {
		delegate.glWindowPos3i(red, green, blue);
	}

	public void glWindowPos3iARB(int red, int green, int blue) {
		delegate.glWindowPos3iARB(red, green, blue);
	}

	public void glWindowPos3iMESA(int red, int green, int blue) {
		delegate.glWindowPos3iMESA(red, green, blue);
	}

	public void glWindowPos3iv(int[] v, int v_offset) {
		delegate.glWindowPos3iv(v, v_offset);
	}

	public void glWindowPos3iv(IntBuffer v) {
		delegate.glWindowPos3iv(v);
	}

	public void glWindowPos3ivARB(int[] v, int v_offset) {
		delegate.glWindowPos3ivARB(v, v_offset);
	}

	public void glWindowPos3ivARB(IntBuffer v) {
		delegate.glWindowPos3ivARB(v);
	}

	public void glWindowPos3ivMESA(int[] v, int v_offset) {
		delegate.glWindowPos3ivMESA(v, v_offset);
	}

	public void glWindowPos3ivMESA(IntBuffer v) {
		delegate.glWindowPos3ivMESA(v);
	}

	public void glWindowPos3s(short red, short green, short blue) {
		delegate.glWindowPos3s(red, green, blue);
	}

	public void glWindowPos3sARB(short red, short green, short blue) {
		delegate.glWindowPos3sARB(red, green, blue);
	}

	public void glWindowPos3sMESA(short red, short green, short blue) {
		delegate.glWindowPos3sMESA(red, green, blue);
	}

	public void glWindowPos3sv(short[] v, int v_offset) {
		delegate.glWindowPos3sv(v, v_offset);
	}

	public void glWindowPos3sv(ShortBuffer v) {
		delegate.glWindowPos3sv(v);
	}

	public void glWindowPos3svARB(short[] v, int v_offset) {
		delegate.glWindowPos3svARB(v, v_offset);
	}

	public void glWindowPos3svARB(ShortBuffer v) {
		delegate.glWindowPos3svARB(v);
	}

	public void glWindowPos3svMESA(short[] v, int v_offset) {
		delegate.glWindowPos3svMESA(v, v_offset);
	}

	public void glWindowPos3svMESA(ShortBuffer v) {
		delegate.glWindowPos3svMESA(v);
	}

	public void glWindowPos4dMESA(double x, double y, double z, double w) {
		delegate.glWindowPos4dMESA(x, y, z, w);
	}

	public void glWindowPos4dvMESA(double[] m, int m_offset) {
		delegate.glWindowPos4dvMESA(m, m_offset);
	}

	public void glWindowPos4dvMESA(DoubleBuffer m) {
		delegate.glWindowPos4dvMESA(m);
	}

	public void glWindowPos4fMESA(float red, float green, float blue,
			float alpha) {
		delegate.glWindowPos4fMESA(red, green, blue, alpha);
	}

	public void glWindowPos4fvMESA(float[] m, int m_offset) {
		delegate.glWindowPos4fvMESA(m, m_offset);
	}

	public void glWindowPos4fvMESA(FloatBuffer m) {
		delegate.glWindowPos4fvMESA(m);
	}

	public void glWindowPos4iMESA(int location, int v0, int v1, int v2) {
		delegate.glWindowPos4iMESA(location, v0, v1, v2);
	}

	public void glWindowPos4ivMESA(int[] v, int v_offset) {
		delegate.glWindowPos4ivMESA(v, v_offset);
	}

	public void glWindowPos4ivMESA(IntBuffer v) {
		delegate.glWindowPos4ivMESA(v);
	}

	public void glWindowPos4sMESA(short x, short y, short z, short w) {
		delegate.glWindowPos4sMESA(x, y, z, w);
	}

	public void glWindowPos4svMESA(short[] v, int v_offset) {
		delegate.glWindowPos4svMESA(v, v_offset);
	}

	public void glWindowPos4svMESA(ShortBuffer v) {
		delegate.glWindowPos4svMESA(v);
	}

	public void glWriteMaskEXT(int stage, int portion, int variable, int input,
			int mapping, int componentUsage) {
		delegate.glWriteMaskEXT(stage, portion, variable, input, mapping,
				componentUsage);
	}

	public boolean isExtensionAvailable(String glExtensionName) {
		return delegate.isExtensionAvailable(glExtensionName);
	}

	public boolean isFunctionAvailable(String glFunctionName) {
		return delegate.isFunctionAvailable(glFunctionName);
	}

	public void setSwapInterval(int interval) {
		delegate.setSwapInterval(interval);
	}
}
